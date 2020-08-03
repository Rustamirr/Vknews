package com.example.vknews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vknews.data.database.dao.NewsDao
import com.example.vknews.data.database.entities.NewsEntity

@Database(
    version = 1,
    entities = [NewsEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}