package com.example.newshub.data.repositoryimpl.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.newshub.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRemoteDataSource {
    fun getTopHeadlines(country: String): Flow<PagingData<Article>>

    fun getSearchedTopHeadlines(
        country: String,
        query: String,
    ): Flow<PagingData<Article>>
}