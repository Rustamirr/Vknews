package com.example.vknews.data.news

import com.example.vknews.data.news.network.NewsNetworkApi
import com.example.vknews.data.news.network.NewsNetworkSource
import com.example.vknews.data.news.network.NewsNetworkSourceImpl
import com.example.vknews.domain.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
interface NewsDataModule {

    companion object {
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
    fun bindNewsNetworkSource(newsNetworkSourceImpl: NewsNetworkSourceImpl): NewsNetworkSource
}