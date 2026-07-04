package com.stoicus.app.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.stoicus.app.feature.onboarding.OnboardingScreen
import com.stoicus.app.feature.morning.HomeScreen
import com.stoicus.app.feature.morning.MorningRitualScreen
import com.stoicus.app.feature.micro.MicroActionsScreen
import com.stoicus.app.feature.evening.EveningRitualScreen
import com.stoicus.app.feature.analytics.AnalyticsScreen
import com.stoicus.app.feature.philosophy.PhilosophyScreen
import com.stoicus.app.feature.gallery.GalleryScreen
import com.stoicus.app.feature.music.MusicScreen
import com.stoicus.app.feature.legal.LegalTextsScreen
import com.stoicus.app.feature.admin.AdminGateScreen
import com.stoicus.app.feature.admin.AdminPanelScreen
import com.stoicus.app.feature.license.LicenseActivationScreen

@Composable
fun StoicusNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Onboarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onOnboardingComplete = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToMorning = { navController.navigate(Screen.MorningRitual.route) },
                onNavigateToMicroActions = { navController.navigate(Screen.MicroActions.route) },
                onNavigateToEvening = { navController.navigate(Screen.EveningRitual.route) },
                onNavigateToAnalytics = { navController.navigate(Screen.Analytics.route) },
                onNavigateToPhilosophy = { navController.navigate(Screen.Philosophy.route) },
                onNavigateToGallery = { navController.navigate(Screen.Gallery.route) },
                onNavigateToMusic = { navController.navigate(Screen.Music.route) }
            )
        }

        composable(Screen.MorningRitual.route) {
            MorningRitualScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.MicroActions.route) {
            MicroActionsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.EveningRitual.route) {
            EveningRitualScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Analytics.route) {
            AnalyticsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Philosophy.route) {
            PhilosophyScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Gallery.route) {
            GalleryScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Music.route) {
            val context = LocalContext.current
            MusicScreen(
                context = context,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // 🆕 Activación de licencia Gumroad
        composable(Screen.LicenseActivation.route) {
            LicenseActivationScreen(
                onNavigateBack = { navController.popBackStack() },
                onLicenseActivated = {
                    navController.navigate(Screen.Home.route) { popUpTo(Screen.Home.route) { inclusive = true } }
                }
            )
        }

        // 🆕 Acceso al panel admin (gate con PIN)
        composable(Screen.AdminGate.route) {
            AdminGateScreen(
                onAdminAuthorized = {
                    navController.navigate(Screen.AdminPanel.route) {
                        popUpTo(Screen.AdminGate.route) { inclusive = true }
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // 🆕 Panel admin (protegido)
        composable(Screen.AdminPanel.route) {
            AdminPanelScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // 🆕 Pantallas legales
        composable(Screen.PrivacyPolicy.route) {
            LegalTextsScreen(
                legalType = LegalTextsScreen.LegalType.PRIVACY_POLICY,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.TermsOfService.route) {
            LegalTextsScreen(
                legalType = LegalTextsScreen.LegalType.TERMS_OF_SERVICE,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.CookiePolicy.route) {
            LegalTextsScreen(
                legalType = LegalTextsScreen.LegalType.COOKIE_POLICY,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
