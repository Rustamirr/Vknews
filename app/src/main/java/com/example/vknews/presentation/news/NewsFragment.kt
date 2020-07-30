package com.example.vknews.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bumptech.glide.Glide
import com.example.vknews.R
import com.example.vknews.databinding.FragmentNewsBinding
import com.example.vknews.domain.news.NewsState
import com.example.vknews.presentation.core.BaseFragment
import com.example.vknews.presentation.core.toLocalDate
import com.example.vknews.presentation.core.toStringFormat
import com.example.vknews.presentation.news.adapter.NewsAdapter
import com.example.vknews.presentation.news.adapter.NewsItem
import com.example.vknews.presentation.news.adapter.NewsViewHolderCreator
import java.time.LocalDate

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsState, NewsPresenter>(), NewsView {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private val adapter: NewsAdapter by lazy {
        NewsAdapter(
            NewsViewHolderCreator(
                Glide.with(this@NewsFragment),
                presenter::onNewsItemClick
            )
        )
    }
    private val newsViewModel: NewsViewModel by viewModels()

    override fun LayoutInflater.createBinding(container: ViewGroup?): FragmentNewsBinding =
        FragmentNewsBinding.inflate(this, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.app_name)
        with(binding) {
            startDate.run {
                setOnClickListener { presenter.onStartDateClick(text.toString().toLocalDate()) }
            }
            endDate.run {
                setOnClickListener { presenter.onEndDateClick(text.toString().toLocalDate()) }
            }
            recyclerView.adapter = adapter
        }
        newsViewModel.observeDatePicker().observe(
            viewLifecycleOwner,
            Observer(presenter::onDateChosen)
        )
    }

    override fun renderStartDate(startDate: LocalDate) {
        binding.startDate.text = startDate.toStringFormat()
    }

    override fun renderEndDate(endDate: LocalDate) {
        binding.endDate.text = endDate.toStringFormat()
    }

    override fun renderList(list: PagedList<NewsItem>) {
        adapter.submitList(list)
    }

    override fun showErrorOccurred() {
        Toast.makeText(requireContext(), "Error occurred", Toast.LENGTH_SHORT).show()
    }
}