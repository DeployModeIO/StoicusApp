package com.stoicus.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_goals")
data class DailyGoal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val pillar: String,
    val goalDescription: String,
    val completed: Boolean = false,
    val completedAt: Long? = null
)
