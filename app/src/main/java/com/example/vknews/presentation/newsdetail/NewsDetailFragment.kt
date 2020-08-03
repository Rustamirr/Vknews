package com.example.vknews.presentation.newsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.vknews.R
import com.example.vknews.databinding.FragmentNewsDetailBinding
import com.example.vknews.domain.core.EmptyState
import com.example.vknews.presentation.core.BaseFragment
import com.example.vknews.presentation.core.toStringFormat
import com.example.vknews.presentation.news.adapter.NewsItem

private const val ARGUMENT_NEWS = "ARGUMENT_NEWS"

class NewsDetailFragment :
    BaseFragment<FragmentNewsDetailBinding, EmptyState, NewsDetailPresenter>(), NewsDetailView {

    companion object {
        fun newInstance(newsItem: NewsItem) = NewsDetailFragment()
            .apply {
                arguments = bundleOf(ARGUMENT_NEWS to newsItem)
            }
    }

    private val newsItem by lazy<NewsItem> {
        requireNotNull(arguments?.getParcelable(ARGUMENT_NEWS))
    }

    override fun LayoutInflater.createBinding(container: ViewGroup?): FragmentNewsDetailBinding =
        FragmentNewsDetailBinding.inflate(this, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.detail_screen)
        with(binding) {
            title.text = newsItem.title
            description.text = newsItem.description
            date.text = newsItem.date.toStringFormat()
            Glide.with(this@NewsDetailFragment)
                .load(newsItem.imageUrl)
                .fitCenter()
                .into(image)
        }
    }
}