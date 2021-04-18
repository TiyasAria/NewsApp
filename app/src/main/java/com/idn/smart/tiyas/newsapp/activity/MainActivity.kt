package com.idn.smart.tiyas.newsapp.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.idn.smart.tiyas.newsapp.BuildConfig
import com.idn.smart.tiyas.newsapp.NewsAdapter
import com.idn.smart.tiyas.newsapp.R
import com.idn.smart.tiyas.newsapp.databinding.ActivityMainBinding
import com.idn.smart.tiyas.newsapp.model.ResponseNews
import com.idn.smart.tiyas.newsapp.network.RetrofitConfig
import org.jetbrains.anko.intentFor
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import retrofit2.Callback

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), View.OnClickListener {

    //membuat vbinding
    private lateinit var mainBinding : ActivityMainBinding

    //membuat untuk adapter
    private lateinit var  newsAdapter: NewsAdapter

    //buat ambil data tanggal sekraang
    val date = getCurrentDateTime()

    private fun getCurrentDateTime(): Date {
       //berisi tanggal sekrang
        return Calendar.getInstance().time
    }

    //buat ngeformat data tanggal  nya
    fun Date.toString(format : String, locale : Locale = Locale.getDefault()) : String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    // untuk men clear suatu activity
    companion object{
        fun getLaunchService (from:Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //cara memanggil menggunakan main binding
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        supportActionBar?.hide()

        // terus untuk yang item2 nya kita panggil menggunakan mainbinding.nama itemnya
        mainBinding.ivProfileSetting.setOnClickListener(this)

        mainBinding.tvDateMain.text = date.toString("dd/MM/yyyy")
        getNews()



    }

    private fun getNews() {
        val loading = ProgressDialog.show(this, "Request Data", "Sabar Bun Baru Buka Data")
        RetrofitConfig.getInstance().getTopHeadlinesNews(BuildConfig.COUNTRY, BuildConfig.API_KEY).enqueue(
            object : Callback<ResponseNews>{
                override fun onResponse(
                    call: Call<ResponseNews>,
                    response: Response<ResponseNews>
                ) {
                    //log itu buat ngecek data (logcat) terus kalau d itu (debug), e (error), kayak isinya si logcat
                    Log.d("Response","yeaay berhasil" + response.body()?.articles)
                    loading.dismiss()

                    if (response.isSuccessful){
                        val status = response.body()?.status
                        if(status.equals("ok")) {
                            Toast.makeText(this@MainActivity, "Data Succes", Toast.LENGTH_SHORT).show()
                            //untuk menampung data artikel
                            val newsData = response.body()?.articles

                            //untuk mengatur data yang paling gede pertama
                            Glide.with(this@MainActivity).load(newsData?.component3()?.urlToImage)
                                .centerCrop().into(mainBinding.ivBreakingItem)
                            mainBinding.tvTitleBreakingItem.text = newsData?.component3()?.title.toString()
                            mainBinding.tvDateRilisBreakingItem.text = newsData?.component3()?.publishedAt.toString()
                            mainBinding.tvAuthorBreakingItem.text = newsData?.component3()?.author.toString()

                            //menyambungkan pda adapter
                            val newsAdapter = NewsAdapter(this@MainActivity, newsData)
                            mainBinding.rvMain.adapter = newsAdapter
                            mainBinding.rvMain.layoutManager = LinearLayoutManager(this@MainActivity)

                        }else{
                            //kalau gagal di local
                            Toast.makeText(this@MainActivity, "Data Failed", Toast.LENGTH_SHORT).show()

                        }
                    }

                }

                //proses gagal pada server
                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    Log.d("response","Failed" + t.localizedMessage)
                    loading.dismiss()
                }

            }
        )


    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.iv_profile_setting -> startActivity(Intent(ProfileActivity.getLaunchService(this)))

        }
    }


}