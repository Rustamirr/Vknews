package com.example.vknews.presentation.navigation

import androidx.fragment.app.DialogFragment
import com.example.vknews.presentation.news.NewsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screen : SupportAppScreen() {

    object News : Screen() {
        override fun getFragment() = NewsFragment.newInstance()
    }

    sealed class Dialog : SupportAppScreen() {
        abstract override fun getFragment(): DialogFragment

        /*data class DatePicker(private val date: LocalDateTime): Dialog() {
            override fun getFragment() = DatePickerDialogFragment()
        }*/
    }
}