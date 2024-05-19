package com.example.newsapp.apiservice

import com.example.newsapp.dataclass.NewsResponse
import retrofit2.Call
import retrofit2.http.GET


interface NewsApiService {
    @GET("Android/news-api-feed/staticResponse.json")
    fun getNews(): Call<NewsResponse>
}