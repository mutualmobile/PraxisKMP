import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.baseio.kmm.db.DriverFactory
import com.baseio.kmm.di.SharedComponent
import com.baseio.kmm.features.trending.GithubTrendingDataModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

val sharedComponent = SharedComponent()

class TrendingReposVM {

    var uiState by mutableStateOf<GithubTrendingDataModel.DataState>(GithubTrendingDataModel.EmptyState)
        private set

    val trendingDataModel = GithubTrendingDataModel(onDataState = { stateNew ->
        uiState = stateNew
    })

    init {
        MainScope().launch {
            setupDriver()
            trendingDataModel.activate()
        }
    }
}

suspend fun setupDriver() {
    sharedComponent.provideGithubTrendingLocal().driver?.let {} ?: run {
        setupDriverInternal()
    }

}

private suspend fun setupDriverInternal() {
    try {
        val driver = DriverFactory().createDriverBlocking()
        val trendingLocal = sharedComponent.provideGithubTrendingLocal()
        trendingLocal.driver = driver
    } catch (ex: Exception) {
        console.log(ex.message)
        console.log("Exception happened here.")
        console.log(ex)
    }
}
