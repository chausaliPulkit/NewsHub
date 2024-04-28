package com.example.newshub.data.repositoryimpl.datasource

import com.example.newshub.data.model.NewsApiResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, page: Int): Response<NewsApiResponse>
}