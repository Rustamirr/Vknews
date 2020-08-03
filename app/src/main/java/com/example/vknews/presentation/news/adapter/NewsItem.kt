package com.example.vknews.presentation.news.adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class NewsItem(
    val id: Long,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val imageUrl: String?
) : Parcelable