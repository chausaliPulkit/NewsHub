package com.example.newshub.presentation.di

import com.example.newshub.data.api.NewsApiService
import com.example.newshub.data.repositoryimpl.datasource.NewsRemoteDataSource
import com.example.newshub.data.repositoryimpl.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(
            newsApiService
        )
    }
}