package com.example.vknews.presentation.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.example.vknews.R

class NewsAdapter(
    private val newsViewHolderCreator: NewsViewHolderCreator
) : PagedListAdapter<NewsItem, NewsViewHolder>(NewsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        newsViewHolderCreator.createGalleryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_news_item, parent, false)
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}