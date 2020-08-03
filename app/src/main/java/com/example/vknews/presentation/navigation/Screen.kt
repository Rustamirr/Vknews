package com.example.vknews.presentation.navigation

import androidx.fragment.app.DialogFragment
import com.example.vknews.presentation.datepicker.DatePickerDate
import com.example.vknews.presentation.datepicker.DatePickerFragment
import com.example.vknews.presentation.news.NewsFragment
import com.example.vknews.presentation.news.adapter.NewsItem
import com.example.vknews.presentation.newsdetail.NewsDetailFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screen : SupportAppScreen() {

    object News : Screen() {
        override fun getFragment() = NewsFragment.newInstance()
    }

    data class NewsDetail(private val newsItem: NewsItem) : Screen() {
        override fun getFragment() = NewsDetailFragment.newInstance(newsItem)
    }

    sealed class Dialog : SupportAppScreen() {
        val tag: String = "TAG_${javaClass.name}"
        abstract override fun getFragment(): DialogFragment

        data class DatePicker(private val datePickerDate: DatePickerDate) : Dialog() {
            override fun getFragment() = DatePickerFragment.newInstance(datePickerDate)
        }
    }
}