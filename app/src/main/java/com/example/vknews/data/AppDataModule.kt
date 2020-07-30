package com.example.vknews.data

import com.example.vknews.data.news.NewsDataModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.vk.com/"

@Module(includes = [NewsDataModule::class])
interface AppDataModule {

    companion object {
        @Singleton
        @Provides
        fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()

        @Singleton
        @Provides
        fun provideOkHttp(): OkHttpClient {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
        }
    }
}