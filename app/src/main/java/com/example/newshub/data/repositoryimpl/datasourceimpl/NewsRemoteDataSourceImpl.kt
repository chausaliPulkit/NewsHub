package com.example.newshub.data.repositoryimpl.datasourceimpl

import com.example.newshub.data.api.NewsApiService
import com.example.newshub.data.model.NewsApiResponse
import com.example.newshub.data.repositoryimpl.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<NewsApiResponse> {
        return newsApiService.getTopHeadlines(country, page)
    }
}