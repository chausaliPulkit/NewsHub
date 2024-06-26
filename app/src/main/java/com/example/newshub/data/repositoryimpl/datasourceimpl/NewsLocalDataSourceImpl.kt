package com.example.newshub.data.repositoryimpl.datasourceimpl

import com.example.newshub.data.db.ArticleDao
import com.example.newshub.data.model.Article
import com.example.newshub.data.repositoryimpl.datasource.NewsLocalDataSource

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
) : NewsLocalDataSource {
    override suspend fun saveArticleToDb(article: Article) {
        articleDao.insert(article)
    }
}