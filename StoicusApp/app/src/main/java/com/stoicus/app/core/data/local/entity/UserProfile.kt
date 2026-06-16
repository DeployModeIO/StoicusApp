package com.stoicus.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val selectedPillars: List<String> = emptyList(),
    val biggestChallenge: String = "",
    val preferredTime: String = "morning",
    val onboardingCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
