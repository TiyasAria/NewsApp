package com.idn.smart.tiyas.newsapp.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable


@SuppressLint("ParcelCreator")
@Parcelize
data class Source(
    @SerializedName("id")
    val id: String? = null ,
    @SerializedName("name")
    val name: String?
) : Parcelable