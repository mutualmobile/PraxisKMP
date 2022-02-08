import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.baseio.kmm.features.trending.GithubTrendingDataModel
import kotlinx.coroutines.flow.MutableStateFlow

class TrendingReposVM {

  var uiState = mutableStateOf<GithubTrendingDataModel.DataState>(GithubTrendingDataModel.EmptyState)
    private set

  private val trendingDataModel = GithubTrendingDataModel(onDataState = { stateNew ->
    uiState.value = stateNew
  })

  init {
    trendingDataModel.activate()
  }
}
