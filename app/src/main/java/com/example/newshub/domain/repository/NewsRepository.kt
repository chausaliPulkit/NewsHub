package com.example.newshub.domain.repository

import com.example.newshub.data.model.Article
import com.example.newshub.data.model.NewsApiResponse
import com.example.newshub.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    //    function for network communication
    suspend fun getNewsHeadLines(country: String, page: Int): Resource<NewsApiResponse>
    suspend fun getSearchedNews(searchQuery: String): Resource<NewsApiResponse>

    //    function related to local database
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}