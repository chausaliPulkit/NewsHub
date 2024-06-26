package com.example.newshub.presentation.di

import com.example.newshub.data.repositoryimpl.NewsRepositoryImpl
import com.example.newshub.data.repositoryimpl.datasource.NewsLocalDataSource
import com.example.newshub.data.repositoryimpl.datasource.NewsRemoteDataSource
import com.example.newshub.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }
}