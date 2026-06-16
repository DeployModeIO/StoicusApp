package com.stoicus.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "micro_actions")
data class MicroAction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val actionType: String,
    val description: String,
    val completed: Boolean = false,
    val completedAt: Long? = null,
    val date: String
)
