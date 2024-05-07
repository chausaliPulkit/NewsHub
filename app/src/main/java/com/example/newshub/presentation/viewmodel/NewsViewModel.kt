package com.example.newshub.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newshub.data.model.Article
import com.example.newshub.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newshub.domain.usecase.GetSearchedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val applicationContext: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
) : AndroidViewModel(applicationContext) {
    val newsHeadlines: Flow<PagingData<Article>> =
        getNewsHeadlinesUseCase.execute("in").cachedIn(viewModelScope)

//    fun getSearchedNews(country: String, query: String) {
//        val apiResult = getSearchedNewsUseCase.execute(country, query)
//            .cachedIn(viewModelScope)
//        searchedNewsHeadlines = apiResult
//    }

//    fun getNewsHeadlines(country: String, page: Int) {
//        viewModelScope.launch {
//            newsHeadlines.postValue(Resource.Loading())
//            try {
//                if (isInternetAvailable(applicationContext)) {
//                    newsHeadlines.postValue(Resource.Loading())
//                    val apiResult = getNewsHeadlinesUseCase.execute(country)
//                    newsHeadlines.postValue(apiResult)
//                } else {
//                    newsHeadlines.postValue(Resource.Error("Internet is not available"))
//                }
//            } catch (e: Exception) {
//                newsHeadlines.postValue(Resource.Error(e.message.toString()))
//            }
//        }
//    }

    @Suppress("DEPRECATION")
    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }
}