package com.example.vknews.presentation

import com.example.vknews.presentation.navigation.NavigationModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Module(
        includes = [
            AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class,
            ActivityBuilder::class,
            NavigationModule::class
        ]
)
interface PresentationModule