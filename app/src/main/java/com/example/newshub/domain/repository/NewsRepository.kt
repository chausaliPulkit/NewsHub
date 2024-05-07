package com.example.newshub.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.newshub.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    //    function for network communication
    fun getNewsHeadLines(
        country: String,
    ): Flow<PagingData<Article>>

    fun getSearchedNews(
        country: String,
        searchQuery: String,
    ): LiveData<PagingData<Article>>

    //    function related to local database
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}