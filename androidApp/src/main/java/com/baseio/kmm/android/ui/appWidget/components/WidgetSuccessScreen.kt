package com.baseio.kmm.android.ui.appWidget.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.background
import androidx.glance.layout.Alignment
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
import com.baseio.kmm.android.ui.appWidget.TrendingReposWidget
import com.baseio.kmm.android.ui.theme.WidgetBodyStyle
import com.baseio.kmm.android.ui.theme.WidgetTitleStyle
import com.baseio.kmm.domain.model.GithubReposItem

@Composable
fun WidgetSuccessScreen(dataToDisplayOnScreen: List<GithubReposItem>) {
    LazyColumn(
        modifier = GlanceModifier.fillMaxSize().padding(horizontal = TrendingReposWidget.WidgetItemRadius.dp)
    ) {
        item {
            Spacer(modifier = GlanceModifier.padding(TrendingReposWidget.SpacerPadding.dp))
        }
        dataToDisplayOnScreen.forEach { githubReposItem ->
            item {
                Column {
                    Row(
                        modifier = GlanceModifier
                            .fillMaxWidth()
                            .padding(TrendingReposWidget.RowPadding.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .cornerRadius(TrendingReposWidget.WidgetItemRadius.dp)
                            .clickable(
                                onClick = actionStartActivity<TrendingReposUI>()
                            ),
                        verticalAlignment = Alignment.Vertical.CenterVertically
                    ) {
                        Image(
                            provider = ImageProvider(R.drawable.widget_placeholder_avatar),
                            contentDescription = null,
                            modifier = GlanceModifier.size(TrendingReposWidget.ImageSize.dp).cornerRadius(TrendingReposWidget.ImageSize.dp)
                        )
                        Spacer(modifier = GlanceModifier.padding(TrendingReposWidget.SpacerPadding.dp))
                        Column {
                            Text(githubReposItem.name.orEmpty(), style = WidgetTitleStyle, maxLines = 1)
                            Text(githubReposItem.url.orEmpty(), style = WidgetBodyStyle, maxLines = 1)
                        }
                    }
                    Spacer(modifier = GlanceModifier.padding((TrendingReposWidget.SpacerPadding / 2).dp))
                }
            }
        }
        item {
            Spacer(modifier = GlanceModifier.padding((TrendingReposWidget.SpacerPadding / 2).dp))
        }
    }
}
