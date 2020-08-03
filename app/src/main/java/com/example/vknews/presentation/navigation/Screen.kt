package com.example.vknews.presentation.navigation

import androidx.fragment.app.DialogFragment
import com.example.vknews.presentation.datepicker.DatePickerDate
import com.example.vknews.presentation.datepicker.DatePickerFragment
import com.example.vknews.presentation.news.NewsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screen : SupportAppScreen() {

    object News : Screen() {
        override fun getFragment() = NewsFragment.newInstance()
    }

    sealed class Dialog : SupportAppScreen() {
        val tag: String = "TAG_${javaClass.name}"
        abstract override fun getFragment(): DialogFragment

        data class DatePicker(private val datePickerDate: DatePickerDate) : Dialog() {
            override fun getFragment() = DatePickerFragment.newInstance(datePickerDate)
        }
    }
}