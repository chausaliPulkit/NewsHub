package com.example.newshub.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.newshub.data.model.Article
import com.example.newshub.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    fun execute(
        country: String,
        searchQuery: String,
    ): LiveData<PagingData<Article>> {
        return newsRepository.getSearchedNews(country, searchQuery)
    }
}