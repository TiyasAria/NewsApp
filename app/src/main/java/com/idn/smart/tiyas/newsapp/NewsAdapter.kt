package com.idn.smart.tiyas.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idn.smart.tiyas.newsapp.activity.DetailActivity
import com.idn.smart.tiyas.newsapp.databinding.ItemNewsBinding
import com.idn.smart.tiyas.newsapp.model.Article
import org.jetbrains.anko.intentFor


/**
nah disini kita akan membuat class adapter karena dia menggunakan recycler maka kita extend (:) ke recycler, dan karena dia
 merupakan adpter maka .adapter, karena adapter ini merupakan jembatan dari tampilan xml ke logic maka ,akan ada data yang
 dimasukkan maka kita buat colection <>, dan isinya nama adapter kita , dan . nama class holder kita, karena recycler ini megang
 view kita , maka harus terdefinisikam dg membuat class holder yang namanya kita kasih .NewsViewHolder, habis tu implement meber
 dan muncul fun ovveride 3 itu, habis tu class holder nya msih merah karena kita blm buat class nya jadi sekarang kita buat class
 viewholder nya , inner class NewsViewHolder yang parameternya isinya view , dan extend ke recyclerview.viewholder kita panggil
 parameter viewnya, lalu jangn lupa untuk yg class nnewsadapter kita kasih param context (isinya), dan datanya yang berupa listNews
 lalu kita panggil List<class modelnya> agr mencegah nullable maka tambahkan (?)
 */

class NewsAdapter(var context: Context, var listNews : List<Article?>?): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //untuk menggaungkan data dan view,
        private val itemBinding = ItemNewsBinding.bind(view)
        fun bind(news: Article) {
            with(itemView) {
                itemBinding.tvTitleItemNews.text = news.title
                itemBinding.tvAuthorItemNews.text = news.author
                itemBinding.tvTimeItemNews.text = news.publishedAt

                Glide.with(context)
                    .load(news.urlToImage)
                    .centerCrop()
                    .into(itemBinding.ivItemNews)

                //ini untuk ke detail
                itemView.setOnClickListener {
                    itemView.context?.startActivity(
                        itemView.context.intentFor<DetailActivity>(
                            "EXTRA_NEWS" to news
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        //penggabungan layout , layout mana yang akan digabungkan
        val view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    //mengatur data sesuai posisi list
    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        holder.bind(listNews?.get(position)!!)
    }

    //menghitung jumlah data nya ada berapa
    override fun getItemCount(): Int = listNews!!.size
}
