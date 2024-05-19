package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(this, emptyList())
        recyclerView.adapter = adapter

        viewModel.newsLiveData.observe(this) { newsArticles ->
            adapter.updateNewsList(newsArticles)
        }

        viewModel.fetchNewsArticles()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_new_to_old -> {
                viewModel.sortNewsArticles(SortOrder.NEW_TO_OLD)
                true
            }
            R.id.sort_old_to_new -> {
                viewModel.sortNewsArticles(SortOrder.OLD_TO_NEW)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}