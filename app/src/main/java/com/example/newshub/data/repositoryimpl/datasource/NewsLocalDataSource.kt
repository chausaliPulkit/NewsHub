package com.example.newshub.data.repositoryimpl.datasource

import com.example.newshub.data.model.Article

interface NewsLocalDataSource {

    suspend fun saveArticleToDb(article: Article)
}