package com.stoicus.app.core.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Bronze,
    onPrimary = DarkBackground,
    primaryContainer = BronzeDark,
    onPrimaryContainer = TextWhite,
    secondary = DeepBlue,
    onSecondary = TextWhite,
    secondaryContainer = DeepBlueLight,
    onSecondaryContainer = TextWhite,
    tertiary = StoicRed,
    onTertiary = TextWhite,
    background = DarkBackground,
    onBackground = TextWhite,
    surface = DarkSurface,
    onSurface = TextWhite,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextGrey,
    outline = TextMuted
)

private val LightColorScheme = lightColorScheme(
    primary = BronzeDark,
    onPrimary = TextWhite,
    primaryContainer = BronzeLight,
    onPrimaryContainer = DarkBackground,
    secondary = DeepBlueDark,
    onSecondary = TextWhite,
    secondaryContainer = DeepBlueLight,
    onSecondaryContainer = TextWhite,
    tertiary = StoicRedLight,
    onTertiary = TextWhite,
    background = TextWhite,
    onBackground = DarkBackground,
    surface = TextWhite,
    onSurface = DarkBackground,
    surfaceVariant = StoneLight,
    onSurfaceVariant = Stone,
    outline = TextMuted
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
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = StoicTypography,
        content = content
    )
}