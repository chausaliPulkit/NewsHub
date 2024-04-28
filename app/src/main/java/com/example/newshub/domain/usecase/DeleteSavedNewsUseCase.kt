package com.example.newshub.domain.usecase

import com.example.newshub.data.model.Article
import com.example.newshub.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}