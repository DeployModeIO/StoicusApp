package com.stoicus.app.navigation

import androidx.compose.runtime.Composable
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
                onNavigateToPhilosophy = { navController.navigate(Screen.Philosophy.route) }
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
    }
}
