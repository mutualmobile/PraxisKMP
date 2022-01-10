package com.baseio.kmm.android.ui.appWidget

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.StartActivityClassAction
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
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
import com.baseio.kmm.android.ui.theme.BaseiOKMMGlanceTheme
import com.baseio.kmm.android.ui.theme.WidgetBodyStyle
import com.baseio.kmm.android.ui.theme.WidgetTitleStyle
import com.baseio.kmm.domain.model.GithubReposItem

class TrendingReposWidget : GlanceAppWidget() {

    private companion object {
        const val WidgetOuterRadius = 24
        const val WidgetItemRadius = 16
        const val SpacerPadding = 8
        const val ImageSize = 60
        const val RowPadding = 16
    }

    @SuppressLint("MutableCollectionMutableState", "RememberReturnType")
    @Composable
    override fun Content() {
        BaseiOKMMGlanceTheme {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .cornerRadius(WidgetOuterRadius.dp)
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

                LazyColumn(
                    modifier = GlanceModifier.fillMaxSize().padding(horizontal = WidgetItemRadius.dp)
                ) {
                    item {
                        Spacer(modifier = GlanceModifier.padding(SpacerPadding.dp))
                    }
                    dataToDisplayOnScreen.forEach { githubReposItem ->
                        item {
                            Column {
                                Row(
                                    modifier = GlanceModifier
                                        .fillMaxWidth()
                                        .padding(RowPadding.dp)
                                        .background(MaterialTheme.colorScheme.surface)
                                        .cornerRadius(WidgetItemRadius.dp)
                                        .clickable(
                                            onClick = StartActivityClassAction(
                                                activityClass = TrendingReposUI::class.java,
                                                parameters = actionParametersOf()
                                            )
                                        ),
                                    verticalAlignment = Alignment.Vertical.CenterVertically
                                ) {
                                    Image(
                                        provider = ImageProvider(R.drawable.widget_placeholder_avatar),
                                        contentDescription = null,
                                        modifier = GlanceModifier.size(ImageSize.dp).cornerRadius(ImageSize.dp)
                                    )
                                    Spacer(modifier = GlanceModifier.padding(SpacerPadding.dp))
                                    Column {
                                        Text(githubReposItem.name.orEmpty(), style = WidgetTitleStyle, maxLines = 1)
                                        Text(githubReposItem.url.orEmpty(), style = WidgetBodyStyle, maxLines = 1)
                                    }
                                }
                                Spacer(modifier = GlanceModifier.padding((SpacerPadding / 2).dp))
                            }
                        }
                    }
                    item {
                        Spacer(modifier = GlanceModifier.padding((SpacerPadding / 2).dp))
                    }
                }
            }
        }
    }
}

class TrendingReposWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = TrendingReposWidget()
}
