package com.example.vknews.presentation.news

import com.example.vknews.domain.news.NewsInfo
import com.example.vknews.presentation.news.adapter.NewsItem

fun NewsInfo.toNewsItem() = NewsItem(id, title, date, imageUrl)