package com.example.newshub.presentation.di

import com.example.newshub.presentation.adapter.NewsAdapter
import com.example.newshub.presentation.adapter.SavedNewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }

    @Singleton
    @Provides
    fun providesSavedNewsAdapter(): SavedNewsAdapter {
        return SavedNewsAdapter()
    }

}