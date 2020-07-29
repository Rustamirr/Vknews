package com.example.vknews

import android.content.Context
import com.example.vknews.data.AppDataModule
import com.example.vknews.domain.core.DomainModule
import com.example.vknews.presentation.PresentationModule
import dagger.Module
import dagger.Provides

@Module(
        includes = [
            PresentationModule::class,
            DomainModule::class,
            AppDataModule::class
        ]
)
interface ApplicationModule {

    companion object {
        @Provides
        fun providesContext(application: App): Context = application
    }
}