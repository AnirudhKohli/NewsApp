package com.example.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.SortOrder
import com.example.newsapp.apiservice.NewsApiService
import com.example.newsapp.apiservice.RetrofitClient
import com.example.newsapp.dataclass.NewsArticle
import com.example.newsapp.dataclass.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val newsLiveData: MutableLiveData<List<NewsArticle>> = MutableLiveData()
    private var newsArticles: List<NewsArticle> = listOf()

    fun fetchNewsArticles() {
        val newsApiService = RetrofitClient.instance.create(NewsApiService::class.java)
        newsApiService.getNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    newsLiveData.postValue(response.body()?.articles)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Handle error
            }
        })
    }

    fun sortNewsArticles(order: SortOrder) {
//        val sortedList = when (order) {
//            SortOrder.NEW_TO_OLD -> newsArticles.sortedByDescending { it.date }
//            SortOrder.OLD_TO_NEW -> newsArticles.sortedBy { it.date }
//        }
//        newsLiveData.postValue(sortedList)
        val sortedList = when (order) {
            SortOrder.NEW_TO_OLD -> newsArticles.sortedByDescending { it.getParsedDate() }
            SortOrder.OLD_TO_NEW -> newsArticles.sortedBy { it.getParsedDate() }
        }
        newsLiveData.postValue(sortedList)

    }
}