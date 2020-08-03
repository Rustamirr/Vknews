package com.example.vknews.presentation.news.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.vknews.databinding.FragmentNewsItemBinding
import com.example.vknews.presentation.core.toStringFormat

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
            title.text = item?.title ?: ""
            date.text = item?.date?.toStringFormat() ?: ""
            glideManager.load(item?.imageUrl)
                .placeholder(android.R.drawable.ic_menu_help)
                .centerCrop()
                .into(image)
            when (item != null) {
                true -> root.setOnClickListener { onNewsItemClick(item) }
                else -> root.setOnClickListener(null)
            }
        }
    }
}