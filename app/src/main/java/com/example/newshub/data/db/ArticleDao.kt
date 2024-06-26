package com.example.newshub.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newshub.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    //    This function will get users saved article from room database
    @Query("SELECT * FROM articles")
    fun getAllSavedArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}