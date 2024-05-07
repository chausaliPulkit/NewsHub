package com.example.newshub.data.repositoryimpl.datasourceimpl

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.newshub.data.api.NewsApiService
import com.example.newshub.data.model.Article
import com.example.newshub.data.repositoryimpl.NewsHubPagingSource
import com.example.newshub.data.repositoryimpl.NewsRepositoryImpl.Companion.NETWORK_PAGE_SIZE
import com.example.newshub.data.repositoryimpl.datasource.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {
    override fun getTopHeadlines(
        country: String,
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = true),
            pagingSourceFactory = {
                NewsHubPagingSource(newsApiService, "", country)
            }
        ).flow
    }

    override fun getSearchedTopHeadlines(
        country: String,
        query: String,
    ): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                NewsHubPagingSource(newsApiService, query, country)
            }
        ).liveData
    }


}