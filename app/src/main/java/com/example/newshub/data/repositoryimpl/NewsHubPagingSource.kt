package com.example.newshub.data.repositoryimpl

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newshub.data.api.NewsApiService
import com.example.newshub.data.model.Article
import com.example.newshub.data.model.NewsApiResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


private const val NEWS_API_STARTING_PAGE_INDEX = 1

class NewsHubPagingSource(
    private val service: NewsApiService,
    private val query: String,
    private val country: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        Log.d("MY_TAG", "NewsHubPagingSource Called")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: NEWS_API_STARTING_PAGE_INDEX
        val apiQuery = query
        return try {
            Log.d("MY_TAG", "NewsHubPagingSource Called")
            var response: Response<NewsApiResponse>
            if (query == "")
                response = service.getTopHeadlines(country, query, position)
            else
                response = service.getSearchedTopHeadlines(country, query, position)
            Log.d(
                "MY_TAG", "$response"
            )
            val article = response.body()!!.articles
            val nextKey = if (article.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = article,
                prevKey = if (position == NEWS_API_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}