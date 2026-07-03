package com.stoicus.app.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object MorningRitual : Screen("morning_ritual")
    object MicroActions : Screen("micro_actions")
    object EveningRitual : Screen("evening_ritual")
    object Analytics : Screen("analytics")
    object Philosophy : Screen("philosophy")
    object Gallery : Screen("gallery")
    object Music : Screen("music")
    object RitualDetail : Screen("ritual_detail/{exerciseId}") {
        fun createRoute(exerciseId: String) = "ritual_detail/$exerciseId"
    }
}
