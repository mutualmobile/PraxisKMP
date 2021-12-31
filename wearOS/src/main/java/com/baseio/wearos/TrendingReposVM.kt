package com.baseio.wearos

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baseio.kmm.db.DriverFactory
import com.baseio.kmm.domain.model.GithubReposItem
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrendingReposVM(app: Application) : AndroidViewModel(app) {
  var uiState by mutableStateOf(TrendingReposUiState(emptyList(), true, message = "Loading..."))
    private set

  init {
    listenToChanges()
    fetchData()
  }

  private fun fetchData() {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        try {
          val data = useCasesComponent.provideFetchTrendingReposUseCase().perform("Kotlin")
          precheckSqlite()
          useCasesComponent.provideSaveTrendingReposUseCase().perform(data)
        } catch (ex: Exception) {
          ex.printStackTrace()
          uiState = uiState.copy(message = ex.message ?: "Error")
        }
      }
    }
  }

  private fun listenToChanges() {
    viewModelScope.launch {
      precheckSqlite()
      useCasesComponent.provideGetLocalReposUseCase().perform(null).collectLatest {
        uiState = TrendingReposUiState(it, false, "")
        uiState = uiState.copy(dataToDisplayOnScreen = it, loading = false)
      }
    }
  }

  private suspend fun precheckSqlite() {
    if (sharedComponent.provideGithubTrendingLocal().driver == null) {
      val driver = DriverFactory(context = getApplication()).createDriverBlocking()
      sharedComponent.provideGithubTrendingLocal().driver = driver
    }
  }
}


data class TrendingReposUiState(
  val dataToDisplayOnScreen: List<GithubReposItem> = emptyList(),
  val loading: Boolean = false,
  val message: String = ""
)
