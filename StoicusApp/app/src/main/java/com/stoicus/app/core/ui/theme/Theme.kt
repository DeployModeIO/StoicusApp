package com.stoicus.app.core.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ChampagneGold,
    onPrimary = Obsidian,
    primaryContainer = ChampagneDeep,
    onPrimaryContainer = GoldGlow,
    secondary = PatinaLight,
    onSecondary = Obsidian,
    secondaryContainer = PatinaGreen,
    onSecondaryContainer = MarbleWhite,
    tertiary = RomanWineLight,
    onTertiary = MarbleWhite,
    tertiaryContainer = RomanWine,
    onTertiaryContainer = GoldGlow,
    background = Obsidian,
    onBackground = TextPrimary,
    surface = ObsidianElevated,
    onSurface = TextPrimary,
    surfaceVariant = Charcoal,
    onSurfaceVariant = TextSecondary,
    outline = TextMuted,
    outlineVariant = Slate,
    error = RomanWineLight,
    onError = MarbleWhite
)

private val LightColorScheme = lightColorScheme(
    primary = ChampagneDeep,
    onPrimary = MarbleWhite,
    primaryContainer = ChampagneLight,
    onPrimaryContainer = Obsidian,
    secondary = PatinaGreen,
    onSecondary = MarbleWhite,
    secondaryContainer = PatinaLight,
    onSecondaryContainer = Obsidian,
    tertiary = RomanWine,
    onTertiary = MarbleWhite,
    tertiaryContainer = RomanWineLight,
    onTertiaryContainer = MarbleWhite,
    background = MarbleWhite,
    onBackground = Obsidian,
    surface = Parchment,
    onSurface = Obsidian,
    surfaceVariant = StoneVeil,
    onSurfaceVariant = Slate,
    outline = TextMuted,
    outlineVariant = StoneVeil,
    error = RomanWine,
    onError = MarbleWhite
)

@Composable
fun StoicusTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Edge-to-edge premium look
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            window.navigationBarColor = android.graphics.Color.TRANSPARENT
            val controller = WindowCompat.getInsetsController(window, view)
            controller.isAppearanceLightStatusBars = !darkTheme
            controller.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = StoicTypography,
        content = content
    )
}