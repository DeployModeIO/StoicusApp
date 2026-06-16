package com.stoicus.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ritual_sessions")
data class RitualSession(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val pillar: String,
    val exerciseType: String,
    val durationMinutes: Int,
    val completedAt: Long = System.currentTimeMillis(),
    val completed: Boolean = true,
    val reflection: String = ""
)
