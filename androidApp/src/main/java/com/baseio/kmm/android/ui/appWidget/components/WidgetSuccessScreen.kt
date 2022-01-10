package com.baseio.kmm.android.ui.appWidget.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import com.baseio.kmm.android.R
import com.baseio.kmm.android.TrendingReposUI
import com.baseio.kmm.android.ui.appWidget.TrendingReposWidgetCallbackApi
import com.baseio.kmm.android.ui.appWidget.WidgetConstants
import com.baseio.kmm.android.ui.theme.WidgetBodyStyle
import com.baseio.kmm.android.ui.theme.WidgetTitleStyle
import com.baseio.kmm.domain.model.GithubReposItem

@Composable
fun WidgetSuccessScreen(dataToDisplayOnScreen: List<GithubReposItem>) {
    LazyColumn(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(horizontal = WidgetConstants.WidgetItemRadius.dp)
    ) {
        item {
            Box(
                modifier = GlanceModifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    provider = ImageProvider(R.drawable.ic_refresh),
                    contentDescription = null,
                    modifier = GlanceModifier
                        .clickable(actionRunCallback<TrendingReposWidgetCallbackApi>())
                        .padding(WidgetConstants.SpacerPadding.dp)
                )
            }
        }
        dataToDisplayOnScreen.forEach { githubReposItem ->
            item {
                Column {
                    Row(
                        modifier = GlanceModifier
                            .fillMaxWidth()
                            .padding(WidgetConstants.RowPadding.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .cornerRadius(WidgetConstants.WidgetItemRadius.dp)
                            .clickable(
                                onClick = actionStartActivity<TrendingReposUI>()
                            ),
                        verticalAlignment = Alignment.Vertical.CenterVertically
                    ) {
                        Image(
                            provider = ImageProvider(R.drawable.widget_placeholder_avatar),
                            contentDescription = null,
                            modifier = GlanceModifier
                                .size(WidgetConstants.ImageSize.dp)
                                .cornerRadius(WidgetConstants.ImageSize.dp)
                        )
                        Spacer(modifier = GlanceModifier.padding(WidgetConstants.SpacerPadding.dp))
                        Column {
                            Text(githubReposItem.name.orEmpty(), style = WidgetTitleStyle, maxLines = 1)
                            Text(githubReposItem.url.orEmpty(), style = WidgetBodyStyle, maxLines = 1)
                        }
                    }
                    Spacer(modifier = GlanceModifier.padding((WidgetConstants.SpacerPadding / 2).dp))
                }
            }
        }
        item {
            Spacer(modifier = GlanceModifier.padding((WidgetConstants.SpacerPadding / 2).dp))
        }
    }
}
