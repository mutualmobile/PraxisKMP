package com.baseio.kmm.android.ui.theme

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.MaterialTheme as Material3Theme
import androidx.glance.LocalContext as GlanceLocalContext

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme()

@Composable
fun BaseiOKMMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    context: Context = LocalContext.current,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    Material3Theme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
internal fun BaseiOKMMGlanceTheme(
    glanceContext: Context = GlanceLocalContext.current,
    darkTheme: Boolean = glanceContext.isDarkTheme,
    content: @Composable () -> Unit
) {
    BaseiOKMMTheme(darkTheme = darkTheme, context = glanceContext, content = content)
}

private val Context.isDarkTheme: Boolean
    get() = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
