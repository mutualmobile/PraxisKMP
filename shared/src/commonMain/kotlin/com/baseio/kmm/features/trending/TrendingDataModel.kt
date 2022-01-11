package com.baseio.kmm.features.trending

import com.baseio.kmm.di.UseCasesComponent
import com.baseio.kmm.domain.model.GithubReposItem
import com.baseio.kmm.mvm.PraxisDataModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class TrendingDataModel(
  private val onDataState: (UiState) -> Unit
) : PraxisDataModel(), KoinComponent {

  private var currentLoadingJob: Job? = null
  private val useCasesComponent = UseCasesComponent()

  private val _trendingStateFlow: MutableStateFlow<UiState> = MutableStateFlow(EmptyState)

  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    _trendingStateFlow.value = ErrorState(throwable)
  }

  override fun activate() {
    listenState()
    readLocalRepositories()
    fetchTrendingRepos()
  }

  override fun destroy() {
    viewModelScope.cancel()
  }

  override fun refresh() {
    fetchTrendingRepos()
  }


  private fun listenState() {
    viewModelScope.launch {
      _trendingStateFlow.collectLatest {
        onDataState(it)
      }
    }
  }

  private fun readLocalRepositories() {
    viewModelScope.launch(exceptionHandler) {
      useCasesComponent.provideGetLocalReposUseCase().perform(input = null).collectLatest { list ->
        _trendingStateFlow.value = SuccessState(list)
      }
    }
  }

  fun filterRecords(search: String? = null) {
    fetchTrendingRepos(search)
  }

  private fun fetchTrendingRepos(search: String? = "kotlin") {
    currentLoadingJob?.cancel()
    currentLoadingJob = viewModelScope.launch(exceptionHandler) {
      _trendingStateFlow.value = LoadingState
      val repos = useCasesComponent.provideFetchTrendingReposUseCase().perform(search)
      useCasesComponent.provideSaveTrendingReposUseCase().perform(repos)
      _trendingStateFlow.value = Complete
    }

  }

  sealed class UiState
  object LoadingState : UiState()
  object EmptyState : UiState()
  object Complete : UiState()
  data class SuccessState(
    val trendingList: List<GithubReposItem>,
  ) : UiState()

  data class ErrorState(var throwable: Throwable) : UiState()
}