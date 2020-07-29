package com.example.vknews.presentation

import com.example.vknews.presentation.mainactivity.MainActivity
import com.example.vknews.presentation.mainactivity.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ContributesAndroidInjector(
            modules = [
                MainActivityModule::class,
                FragmentBuilder::class
            ]
    )
    fun mainActivityInjector(): MainActivity
}