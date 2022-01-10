package com.baseio.kmm.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.glance.text.TextDecoration
import androidx.glance.unit.ColorProvider
import androidx.glance.text.TextStyle as GlanceTextStyle

// Set of Material typography styles to start with
val Typography = Typography(
  bodyMedium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  )
  /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val WidgetTitleStyle
    @Composable get() = GlanceTextStyle(
        fontSize = 16.sp,
        fontWeight = androidx.glance.text.FontWeight.Bold,
        color = ColorProvider(MaterialTheme.colorScheme.onSurface),
    )

val WidgetBodyStyle
    @Composable get() = GlanceTextStyle(
        fontSize = 12.sp,
        fontWeight = androidx.glance.text.FontWeight.Normal,
        color = ColorProvider(MaterialTheme.colorScheme.primary.copy(alpha = 0.75f)),
        textDecoration = TextDecoration.Underline
    )
