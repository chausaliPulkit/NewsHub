package com.example.newshub.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newshub.domain.usecase.GetNewsHeadlinesUseCase

class NewsViewModelFactory(
    private val applicationContext: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(applicationContext, getNewsHeadlinesUseCase) as T
    }
}