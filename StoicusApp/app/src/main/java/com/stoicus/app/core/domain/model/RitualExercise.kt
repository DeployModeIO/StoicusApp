package com.stoicus.app.core.domain.model

data class RitualExercise(
    val id: String,
    val title: String,
    val description: String,
    val durationMinutes: Int,
    val pillar: Pillar,
    val philosopher: String,
    val steps: List<String>,
    val reflectionPrompts: List<String>
)
