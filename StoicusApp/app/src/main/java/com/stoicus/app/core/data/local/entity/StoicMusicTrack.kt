package com.stoicus.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stoic_music")
data class StoicMusicTrack(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val artist: String,
    val duration: Long, // in seconds
    val audioUrl: String,
    val category: String, // "meditation", "ambient", "focus", "sleep"
    val mood: String = "calm", // "calm", "energetic", "melancholic"
    val favorited: Boolean = false,
    val isPlaying: Boolean = false
)
