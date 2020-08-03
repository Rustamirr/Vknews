package com.example.vknews.data.authorization

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val PREFERENCE_FILE_NAME = "ApplicationPreference"

@Module
interface AuthorizationDataModule {

    companion object {
        @Singleton
        @Provides
        fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Binds
    fun bindTokenSource(tokenSourceImpl: TokenSourceImpl): TokenSource
}