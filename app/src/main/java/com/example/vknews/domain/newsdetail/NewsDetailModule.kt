package com.example.vknews.domain.newsdetail

import dagger.Binds
import dagger.Module

@Module
interface NewsDetailModule {

    @Binds
    fun bindNewsDetailInteractor(newsDetailModel: NewsDetailModel): NewsDetailInteractor
}