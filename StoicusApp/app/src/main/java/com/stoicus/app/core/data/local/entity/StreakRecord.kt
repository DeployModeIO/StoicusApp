package com.stoicus.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "streak_records")
data class StreakRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val morningRitualCompleted: Boolean = false,
    val eveningRitualCompleted: Boolean = false,
    val microActionsCompleted: Int = 0,
    val moodScore: Int = 0
)
