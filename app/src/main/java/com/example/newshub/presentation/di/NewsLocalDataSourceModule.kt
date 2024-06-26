package com.example.newshub.presentation.di

import com.example.newshub.data.db.ArticleDao
import com.example.newshub.data.repositoryimpl.datasource.NewsLocalDataSource
import com.example.newshub.data.repositoryimpl.datasourceimpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsLocalDataSourceModule {

    @Provides
    @Singleton
    fun providesNewsLocalDataSource(articleDao: ArticleDao): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDao)
    }
}