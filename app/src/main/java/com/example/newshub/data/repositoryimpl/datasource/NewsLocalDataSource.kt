package com.example.newshub.data.repositoryimpl.datasource

import com.example.newshub.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun saveArticleToDb(article: Article)

    fun getSavedArticles(): Flow<List<Article>>

    suspend fun deleteArticleFromDb(article: Article)
}