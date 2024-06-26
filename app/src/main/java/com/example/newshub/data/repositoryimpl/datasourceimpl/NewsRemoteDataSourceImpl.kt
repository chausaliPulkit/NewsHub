package com.example.newshub.data.repositoryimpl.datasourceimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newshub.data.api.NewsApiService
import com.example.newshub.data.model.Article
import com.example.newshub.data.repositoryimpl.NewsHubRemotePagingSource
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
                NewsHubRemotePagingSource(newsApiService, "", country)
            }
        ).flow
    }

    override fun getSearchedTopHeadlines(
        country: String,
        query: String,
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                NewsHubRemotePagingSource(newsApiService, query, country)
            }
        ).flow
    }


}