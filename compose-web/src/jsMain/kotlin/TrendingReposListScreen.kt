import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.baseio.kmm.features.trending.GithubTrendingDataModel
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.href
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Progress
import org.jetbrains.compose.web.dom.Text

@Composable
fun TrendingReposListScreen(trendingReposVM: TrendingReposVM) {
    val uiState = trendingReposVM.uiState
    var currentSearchText by remember { mutableStateOf("") }

    H1 {
        Text("Trending Kotlin Repositories")
    }

    Div {
        Input(InputType.Text) {
            onChange { newTextState ->
                currentSearchText = newTextState.value
            }
        }
        Button(
            attrs = {
                style {
                    marginLeft(8.px)
                }
                onClick {
                    trendingReposVM.trendingDataModel.filterRecords(search = currentSearchText)
                }
            }
        ) {
            Text("Search")
        }
    }

    when (uiState) {
        is GithubTrendingDataModel.LoadingState -> Progress()
        is GithubTrendingDataModel.ErrorState -> {
            Text(uiState.throwable.message ?: "Unexpected error occurred!")
        }
        is GithubTrendingDataModel.SuccessState -> {
            uiState.trendingList.forEach { repoItem ->
                Div(
                    attrs = {
                        style {
                            paddingTop(16.px)
                            paddingLeft(16.px)
                        }
                    }
                ) {
                    Img(
                        src = repoItem.avatar ?: "http://via.placeholder.com/640x360",
                        attrs = {
                            style {
                                width(100.px)
                            }
                        }
                    )
                }
                Div(
                    attrs = {
                        style {
                            paddingLeft(16.px)
                            paddingTop(16.px)
                        }
                    }
                ) {
                    Text("${repoItem.author}: ${repoItem.name}")
                }
                Div(
                    attrs = {
                        style {
                            paddingLeft(16.px)
                            paddingBottom(16.px)
                        }
                    }
                ) {
                    A(
                        attrs = {
                            href(repoItem.url.orEmpty())
                        }
                    ) {
                        Text(repoItem.url.orEmpty())
                    }
                }
            }
        }
        else -> Unit
    }
}