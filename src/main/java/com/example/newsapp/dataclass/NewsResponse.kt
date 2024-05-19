package com.example.newsapp.dataclass

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class NewsResponse(
    val articles: List<NewsArticle>
)



data class NewsArticle(
    val title: String,
    val url: String,
    val date: String // Ensure date field is present
) {
    fun getParsedDate(): Date? {
        return try {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).parse(date)
        } catch (e: Exception) {
            null
        }
    }
}
