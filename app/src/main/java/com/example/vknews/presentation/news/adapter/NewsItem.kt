package com.example.vknews.presentation.news.adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
    val id: Int,
    val title: String,
    val url: String
) : Parcelable