package com.example.vknews.domain.news

import java.time.LocalDateTime

data class NewsFeed(
    val pageKey: String?,
    val newsInfo: List<NewsInfo>
)

data class NewsInfo(
    val id: Long,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val imageUrl: String?
)