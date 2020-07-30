package com.example.vknews.domain.news

import dagger.Binds
import dagger.Module

@Module
interface NewsModule {

    @Binds
    fun bindNewsInteractor(newsModel: NewsModel): NewsInteractor
}