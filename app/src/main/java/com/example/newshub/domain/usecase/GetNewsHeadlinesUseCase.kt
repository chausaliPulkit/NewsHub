package com.example.newshub.domain.usecase

import com.example.newshub.data.model.NewsApiResponse
import com.example.newshub.data.util.Resource
import com.example.newshub.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country: String, page: Int): Resource<NewsApiResponse> {
        return newsRepository.getNewsHeadLines(country, page)
    }
}