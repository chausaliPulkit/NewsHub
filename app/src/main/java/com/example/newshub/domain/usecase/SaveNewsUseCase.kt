package com.example.newshub.domain.usecase

import com.example.newshub.data.model.Article
import com.example.newshub.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.saveNews(article)
}