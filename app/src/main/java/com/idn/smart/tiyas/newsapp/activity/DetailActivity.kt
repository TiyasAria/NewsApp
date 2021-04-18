package com.idn.smart.tiyas.newsapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.idn.smart.tiyas.newsapp.R
import com.idn.smart.tiyas.newsapp.databinding.ActivityDetailBinding
import com.idn.smart.tiyas.newsapp.databinding.ActivitySignInBinding
import com.idn.smart.tiyas.newsapp.model.Article

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NEWS = "extra_news"
    }
    private lateinit var detailActivityBinding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        detailActivityBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)
        supportActionBar?.hide()

        detailActivityBinding.fbBackDetail.setOnClickListener {
            startActivity(Intent(MainActivity.getLaunchService(this)))
        }

        val news = intent.getParcelableExtra<Article>("EXTRA_NEWS") as Article
        Glide.with(this).load(news.urlToImage).into(detailActivityBinding.ivDetail)
        detailActivityBinding.tvDetailTitle.text = news.title.toString()
        detailActivityBinding.tvNameDetail.text = news.author.toString()
        detailActivityBinding.tvDateDetail.text = news.publishedAt.toString()
        detailActivityBinding.tvDescDetail.text = news.description.toString()
        detailActivityBinding.tvContentDetail.text = news.content.toString()

    }
}