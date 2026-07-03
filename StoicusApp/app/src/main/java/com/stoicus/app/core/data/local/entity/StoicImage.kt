package com.stoicus.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stoic_images")
data class StoicImage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val category: String, // "statues", "artwork", "symbols", "manuscripts"
    val philosopher: String? = null,
    val favorited: Boolean = false
)
