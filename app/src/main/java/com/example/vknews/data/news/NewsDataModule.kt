package com.example.vknews.data.news

import dagger.Module

@Module
interface NewsDataModule {

    /*companion object {
        @Singleton
        @Provides
        fun provideNewsNetworkApi(retrofit: Retrofit): NewsNetworkApi =
            retrofit.create(NewsNetworkApi::class.java)
    }

    @Singleton
    @Binds
    fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Singleton
    @Binds
    fun bindNewsNetworkSource(newsNetworkSourceImpl: NewsNetworkSourceImpl): NewsNetworkSource*/
}