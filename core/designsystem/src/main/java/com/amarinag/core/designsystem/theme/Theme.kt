package com.amarinag.core.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@VisibleForTesting
val LightColorScheme = lightColorScheme(
    primary = Mint80,
    onPrimary = Mint20,
    primaryContainer = Mint30,
    onPrimaryContainer = Mint90,
    secondary = Graphite80,
    onSecondary = Graphite20,
    secondaryContainer = Graphite30,
    onSecondaryContainer = Graphite90,
    tertiary = SlateGray80,
    onTertiary = SlateGray20,
    tertiaryContainer = SlateGray30,
    onTertiaryContainer = SlateGray90,
    error = Rose80,
    onError = Rose20,
    errorContainer = Rose30,
    onErrorContainer = Rose90,
    background = Charcoal10,
    onBackground = Charcoal90,
    surface = Charcoal10,
    onSurface = Charcoal90,
    surfaceVariant = SlateGray30,
    onSurfaceVariant = SlateGray80,
    inverseSurface = Charcoal90,
    inverseOnSurface = Charcoal10,
    outline = SlateGray80,
)

@VisibleForTesting
val DarkColorScheme = darkColorScheme(
    primary = Mint40,
    onPrimary = Color.White,
    primaryContainer = Mint90,
    onPrimaryContainer = Mint10,
    secondary = Graphite40,
    onSecondary = Color.White,
    secondaryContainer = Graphite90,
    onSecondaryContainer = Graphite10,
    tertiary = SlateGray40,
    onTertiary = Color.White,
    tertiaryContainer = SlateGray90,
    onTertiaryContainer = SlateGray10,
    error = Rose40,
    onError = Color.White,
    errorContainer = Rose90,
    onErrorContainer = Rose10,
    background = Charcoal90,
    onBackground = Charcoal10,
    surface = Charcoal90,
    onSurface = Charcoal10,
    surfaceVariant = SlateGray80,
    onSurfaceVariant = SlateGray30,
    inverseSurface = Charcoal10,
    inverseOnSurface = Charcoal90,
    outline = SlateGray80,
)


@Composable
fun MovieExplorerTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        // Dynamic color is available on Android 12+
        dynamicColor: Boolean = true,
        content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
    )
}