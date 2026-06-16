package com.stoicus.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_entries")
data class MoodEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val moodScore: Int,
    val stressLevel: Int,
    val energyLevel: Int,
    val reflection: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val date: String
)
