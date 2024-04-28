package com.example.newshub.domain.usecase

import com.example.newshub.data.model.NewsApiResponse
import com.example.newshub.data.util.Resource
import com.example.newshub.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(searchQuery: String): Resource<NewsApiResponse> {
        return newsRepository.getSearchedNews(searchQuery)
    }
}