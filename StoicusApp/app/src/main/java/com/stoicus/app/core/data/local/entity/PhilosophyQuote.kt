package com.stoicus.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "philosophy_quotes")
data class PhilosophyQuote(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val author: String,
    val text: String,
    val source: String = "",
    val pillar: String,
    val favorited: Boolean = false
)
