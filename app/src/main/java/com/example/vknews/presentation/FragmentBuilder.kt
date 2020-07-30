package com.example.vknews.presentation

import com.example.vknews.presentation.datepicker.DatePickerFragment
import com.example.vknews.presentation.datepicker.DatePickerFragmentModule
import com.example.vknews.presentation.news.NewsFragment
import com.example.vknews.presentation.news.NewsFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(
        modules = [
            NewsFragmentModule::class
        ]
    )
    fun newsFragmentInjector(): NewsFragment

    @ContributesAndroidInjector(
        modules = [
            DatePickerFragmentModule::class
        ]
    )
    fun datePickerFragmentInjector(): DatePickerFragment
}