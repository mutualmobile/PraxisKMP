package com.baseio.kmm.features.trending

import com.baseio.kmm.di.UseCasesComponent
import com.baseio.kmm.domain.model.GithubReposItem
import com.baseio.kmm.datamodel.PraxisDataModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class GithubTrendingDataModel(
  private val onDataState: (DataState) -> Unit
) : PraxisDataModel(), KoinComponent {

  private var currentLoadingJob: Job? = null
  private val useCasesComponent = UseCasesComponent()

  private val _trendingStateFlow: MutableStateFlow<DataState> = MutableStateFlow(EmptyState)

  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    _trendingStateFlow.value = ErrorState(throwable)
  }

  override fun activate() {
    listenState()
    readLocalRepositories()
    fetchTrendingRepos()
  }

  override fun destroy() {
    dataModelScope.cancel()
  }

  override fun refresh() {
    fetchTrendingRepos()
  }


  private fun listenState() {
    dataModelScope.launch {
      _trendingStateFlow.collectLatest {
        onDataState(it)
      }
    }
  }

  private fun readLocalRepositories() {
    dataModelScope.launch(exceptionHandler) {
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
    currentLoadingJob = dataModelScope.launch(exceptionHandler) {
      _trendingStateFlow.value = LoadingState
      val repos = useCasesComponent.provideFetchTrendingReposUseCase().perform(search)
      useCasesComponent.provideSaveTrendingReposUseCase().perform(repos)
    }

  }

  sealed class DataState
  object LoadingState : DataState()
  object EmptyState : DataState()
  object Complete : DataState()
  data class SuccessState(
    val trendingList: List<GithubReposItem>,
  ) : DataState()

  data class ErrorState(var throwable: Throwable) : DataState()
}