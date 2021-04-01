package com.idn.smart.tiyas.newsapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idn.smart.tiyas.newsapp.model.Article

/**
nah disini kita akan membuat class adapter karena dia menggunakan recycler maka 
 */

class NewsAdapter(var context: Context, var listNews : List<Article?>?): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}