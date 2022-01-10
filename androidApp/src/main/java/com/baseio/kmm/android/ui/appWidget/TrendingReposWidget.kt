package com.baseio.kmm.android.ui.appWidget

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text

class TrendingReposWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hello world!",
            )
        }
    }
}

class TrendingReposWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = TrendingReposWidget()
}
