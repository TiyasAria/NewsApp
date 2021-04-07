package com.idn.smart.tiyas.newsapp.network



import com.google.gson.GsonBuilder
import com.idn.smart.tiyas.newsapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {
    //buat akses portnya (itu http logging) ibaratnya kamu udah ke server tapi kamu mau lewat pintu\jendela mana
    val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    //untuk menghubungkan ke client nya
    val client = OkHttpClient.Builder().addInterceptor(interceptor)
        .retryOnConnectionFailure(true)
            //ketika prosesnya lebih dr 30 detik , berarti datanya gagall bisa karena ga ada koneksi internet
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    //untuk convert menjadi gson ,
    val gson = GsonBuilder()
        .setLenient()
        .create()


    //entry data
    //buat atur url mana yang mau dieksekusi
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
            //buat convert ke gson
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    //function untuk menyambung end point dari class api service
    fun getInstance() : ApiService = retrofit.create(ApiService::class.java)






}