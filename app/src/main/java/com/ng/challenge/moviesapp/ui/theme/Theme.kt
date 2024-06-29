package com.ng.challenge.moviesapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColors(
    primary = BlackPrimary,
    secondary = YellowSecondary,
    primaryVariant = BlackVariant,
    onPrimary = WhiteOnPrimary,
    background = BackgroundDark,
)

private val LightColorScheme = lightColors(
    primary = YellowSecondary,
    secondary = BlackSecondary,
    primaryVariant = YellowVariant,
    onPrimary = WhiteOnPrimary,
    background = BackgroundLight,

    /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MoviesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}