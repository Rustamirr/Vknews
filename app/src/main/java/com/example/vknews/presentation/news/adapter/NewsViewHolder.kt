package com.example.vknews.presentation.news.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.vknews.databinding.FragmentNewsItemBinding

class NewsViewHolderCreator(
    private val glideManager: RequestManager,
    private val onNewsItemClick: (newsItem: NewsItem) -> Unit
) {
    fun createGalleryViewHolder(itemView: View) =
        NewsViewHolder(itemView, glideManager, onNewsItemClick)
}

class NewsViewHolder(
    itemView: View,
    private val glideManager: RequestManager,
    private val onNewsItemClick: (newsItem: NewsItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = FragmentNewsItemBinding.bind(itemView)

    fun bind(item: NewsItem?) {
        with(binding) {
            /*glideManager.load(item?.url)
                .placeholder(android.R.drawable.ic_menu_help)
                .centerCrop()
                .into(ateaphoto)
            when (item != null) {
                true -> root.setOnClickListener { onNewsItemClick(item) }
                else -> root.setOnClickListener(null)
            }*/
        }
    }
}