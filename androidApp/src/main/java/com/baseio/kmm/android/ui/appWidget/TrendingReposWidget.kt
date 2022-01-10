package com.baseio.kmm.android.ui.appWidget

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import com.baseio.kmm.android.ui.appWidget.components.WidgetSuccessScreen
import com.baseio.kmm.android.ui.theme.BaseiOKMMGlanceTheme
import com.baseio.kmm.domain.model.GithubReposItem

class TrendingReposWidget : GlanceAppWidget() {

    companion object {
        const val WidgetOuterRadius = 24
        const val WidgetItemRadius = 16
        const val SpacerPadding = 8
        const val ImageSize = 60
        const val RowPadding = 16
        const val FailedScreenMsg = "No data to display for now ¯\\_(ツ)_/¯"
    }

    @SuppressLint("MutableCollectionMutableState", "RememberReturnType")
    @Composable
    override fun Content() {
        BaseiOKMMGlanceTheme {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .cornerRadius(WidgetOuterRadius.dp),
                contentAlignment = Alignment.Center
            ) {
                val dataToDisplayOnScreen by remember { mutableStateOf(mutableListOf<GithubReposItem>()) }
                remember {
                    repeat(10) { index ->
                        dataToDisplayOnScreen.add(
                            GithubReposItem(
                                "Author #$index",
                                "https://avatars.githubusercontent.com/u/50905019",
                                "",
                                "",
                                "AuthorRepo$index",
                                "www.github.com/author${index}Repository",
                            )
                        )
                    }
                }
                if (dataToDisplayOnScreen.isEmpty()) {
                    Text(FailedScreenMsg)
                } else {
                    WidgetSuccessScreen(dataToDisplayOnScreen)
                }
            }
        }
    }
}

class TrendingReposWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = TrendingReposWidget()
}
