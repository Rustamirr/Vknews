package com.example.vknews.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DATABASE_NAME = "NEWS"

@Module
interface DatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

        @Provides
        @Singleton
        fun provideNewsDao(appDatabase: AppDatabase) = appDatabase.getNewsDao()
    }
}