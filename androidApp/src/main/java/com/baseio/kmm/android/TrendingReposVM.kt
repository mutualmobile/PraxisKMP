package com.baseio.kmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baseio.kmm.features.trending.GithubTrendingDataModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TrendingReposVM : ViewModel() {

  var uiState = MutableStateFlow<GithubTrendingDataModel.DataState>(GithubTrendingDataModel.EmptyState)
    private set

  private val trendingDataModel = GithubTrendingDataModel(onDataState = { stateNew ->
    viewModelScope.launch {
      uiState.emit(stateNew)
    }
  })

  init {
    viewModelScope.launch {
      delay(1500)
      trendingDataModel.activate()
    }
  }

  override fun onCleared() {
    super.onCleared()
    trendingDataModel.destroy()
  }
}


