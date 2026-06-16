package com.stoicus.app.core.domain.model

data class AnalyticsData(
    val totalSessions: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val averageMood: Float = 0f,
    val averageStress: Float = 0f,
    val sessionsByPillar: Map<String, Int> = emptyMap(),
    val moodTrend: List<Float> = emptyList(),
    val stressTrend: List<Float> = emptyList(),
    val virtueScores: Map<String, Float> = emptyMap()
)
