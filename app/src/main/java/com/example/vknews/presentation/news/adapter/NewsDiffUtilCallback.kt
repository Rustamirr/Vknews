package com.example.vknews.presentation.news.adapter

import androidx.recyclerview.widget.DiffUtil

class NewsDiffUtilCallback : DiffUtil.ItemCallback<NewsItem>() {

    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem) =
        oldItem == newItem
}