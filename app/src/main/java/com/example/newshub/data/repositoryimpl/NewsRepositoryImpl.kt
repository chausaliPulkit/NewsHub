package com.example.newshub.data.repositoryimpl

import com.example.newshub.data.model.Article
import com.example.newshub.data.model.NewsApiResponse
import com.example.newshub.data.repositoryimpl.datasource.NewsRemoteDataSource
import com.example.newshub.data.util.Resource
import com.example.newshub.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,

    ) : NewsRepository {

    override suspend fun getNewsHeadLines(country: String, page: Int): Resource<NewsApiResponse> {
        return responseToResourceUtil(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<NewsApiResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    private fun responseToResourceUtil(response: Response<NewsApiResponse>):
            Resource<NewsApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}