package com.example.newshub.data.repositoryimpl

import androidx.paging.PagingData
import com.example.newshub.data.model.Article
import com.example.newshub.data.repositoryimpl.datasource.NewsLocalDataSource
import com.example.newshub.data.repositoryimpl.datasource.NewsRemoteDataSource
import com.example.newshub.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource

) : NewsRepository {
    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

    override fun getNewsHeadLines(country: String): Flow<PagingData<Article>> {
        return newsRemoteDataSource.getTopHeadlines(country)
    }

    override fun getSearchedNews(
        country: String,
        searchQuery: String,
    ): Flow<PagingData<Article>> {
        return newsRemoteDataSource.getSearchedTopHeadlines(
            country,
            searchQuery,
        )
    }

    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticleToDb(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteArticleFromDb(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedArticles()
    }

//    private fun responseToResourceUtil(response: Flow<PagingData<Article>>):
//            Resource<Flow<PagingData<Article>>> {
//        if (response != null) {
//            response?.let { result ->
//                return Resource.Success(result)
//            }
//        }
//        return Resource.Error("Error in Fetching Data")
//    }


}