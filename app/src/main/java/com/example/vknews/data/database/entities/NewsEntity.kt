package com.example.vknews.data.database.entities

import androidx.room.Entity

@Entity(
    tableName = "News",
    primaryKeys = ["id"]
)
data class NewsEntity(
    val id: Long,
    val title: String,
    val description: String,
    val date: Long,
    val imageUrl: String
)