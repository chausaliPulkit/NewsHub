package com.example.newshub.presentation.di

import android.app.Application
import com.example.newshub.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newshub.domain.usecase.GetSearchedNewsUseCase
import com.example.newshub.domain.usecase.SaveNewsUseCase
import com.example.newshub.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesNewsViewModelFactory(
        applicationContext: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        saveNewsUseCase: SaveNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            applicationContext,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase,
            saveNewsUseCase
        )
    }
}