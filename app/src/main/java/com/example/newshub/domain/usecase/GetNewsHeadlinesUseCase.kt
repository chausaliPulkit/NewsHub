package com.example.newshub.domain.usecase

import androidx.paging.PagingData
import com.example.newshub.data.model.Article
import com.example.newshub.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {

    fun execute(country: String): Flow<PagingData<Article>> {
        return newsRepository.getNewsHeadLines(country)
    }
}