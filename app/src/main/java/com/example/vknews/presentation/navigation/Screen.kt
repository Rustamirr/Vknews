package com.example.vknews.presentation.navigation

import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screen : SupportAppScreen() {

    object News : Screen() {
        override fun getFragment() = TODO()//News.newInstance()
    }

    data class NewsDetail(private val newsId: Int) : Screen() {
        override fun getFragment() = TODO()//NewsDetail.newInstance(newsId)
    }
}