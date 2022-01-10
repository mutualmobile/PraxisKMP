package com.baseio.kmm.android.ui.appWidget

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.updateAll
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.baseio.kmm.android.TrendingReposVM
import com.baseio.kmm.android.ui.appWidget.components.WidgetSuccessScreen
import com.baseio.kmm.android.ui.theme.BaseiOKMMGlanceTheme
import com.baseio.kmm.domain.model.GithubReposItem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object WidgetConstants {
    const val WidgetOuterRadius = 24
    const val WidgetItemRadius = 16
    const val SpacerPadding = 8
    const val ImageSize = 60
    const val RowPadding = 16
    const val FailedScreenMsg = "No data to display for now ¯\\_(ツ)_/¯\nClick on this text to refresh"
}

class TrendingReposWidget(
    private val dataToDisplayOnScreen: List<GithubReposItem>,
    private val trendingReposVM: TrendingReposVM
) : GlanceAppWidget() {

    @SuppressLint("MutableCollectionMutableState", "RememberReturnType")
    @Composable
    override fun Content() {
        BaseiOKMMGlanceTheme {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .cornerRadius(WidgetConstants.WidgetOuterRadius.dp),
                contentAlignment = Alignment.Center
            ) {
                if (trendingReposVM.uiState.loading) {
                    Text("Loading...")
                } else {
                    if (dataToDisplayOnScreen.isEmpty()) {
                        Text(
                            WidgetConstants.FailedScreenMsg,
                            modifier = GlanceModifier.clickable(actionRunCallback<TrendingReposWidgetCallbackApi>()),
                            style = TextStyle(textAlign = TextAlign.Center)
                        )
                    } else {
                        WidgetSuccessScreen(dataToDisplayOnScreen)
                    }
                }
            }
        }
    }
}

class TrendingReposWidgetReceiver : GlanceAppWidgetReceiver(), KoinComponent {
    private val trendingReposVM by inject<TrendingReposVM>()

    override val glanceAppWidget: GlanceAppWidget
        get() = TrendingReposWidget(trendingReposVM.uiState.dataToDisplayOnScreen, trendingReposVM)
}

class TrendingReposWidgetCallbackApi : ActionCallback, KoinComponent {
    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        val trendingReposVM by inject<TrendingReposVM>()
        TrendingReposWidget(trendingReposVM.uiState.dataToDisplayOnScreen, trendingReposVM).updateAll(context)
    }
}
