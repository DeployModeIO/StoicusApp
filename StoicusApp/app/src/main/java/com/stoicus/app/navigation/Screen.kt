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
    // 🆕 Rutas para licencia, admin y legal
    object LicenseActivation : Screen("license_activation")
    object AdminGate : Screen("admin_gate")
    object AdminPanel : Screen("admin_panel")
    object PrivacyPolicy : Screen("privacy_policy")
    object TermsOfService : Screen("terms_of_service")
    object CookiePolicy : Screen("cookie_policy")
}
