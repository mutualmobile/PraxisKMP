package com.baseio.kmm.mvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class PraxisDataModel {
  protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)


  abstract fun activate()
  abstract fun destroy()
  abstract fun refresh()
}