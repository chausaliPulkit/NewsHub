package com.example.newshub.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.newshub.data.db.ArticleDao
import com.example.newshub.data.db.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesNewsDataBase(app: Application): ArticleDataBase {
        return Room.databaseBuilder(app, ArticleDataBase::class.java, "news_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsDao(articleDataBase: ArticleDataBase): ArticleDao {
        return articleDataBase.getArticleDao()
    }
}