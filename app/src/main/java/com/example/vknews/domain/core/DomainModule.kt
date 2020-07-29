package com.example.vknews.domain.core

import com.example.vknews.presentation.core.LoggerImpl
import com.example.vknews.presentation.core.SchedulersImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Singleton
    @Binds
    fun bindSchedulers(schedulers: SchedulersImpl): Schedulers

    @Singleton
    @Binds
    fun bindLogger(loggerImpl: LoggerImpl): Logger
}