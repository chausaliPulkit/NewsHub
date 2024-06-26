package com.example.newshub.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newshub.domain.usecase.DeleteSavedNewsUseCase
import com.example.newshub.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newshub.domain.usecase.GetSavedNewsUseCase
import com.example.newshub.domain.usecase.GetSearchedNewsUseCase
import com.example.newshub.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val applicationContext: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            applicationContext,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase,
            saveNewsUseCase,
            getSavedNewsUseCase,
            deleteSavedNewsUseCase
        ) as T
    }
}