package com.baseio.wearos

import android.app.Application
import com.baseio.kmm.di.SharedComponent
import com.baseio.kmm.di.UseCasesComponent
import com.baseio.kmm.di.initSqlDelightExperimentalDependencies

val sharedComponent = SharedComponent()
val useCasesComponent = UseCasesComponent()

class PraxisApp : Application() {
  override fun onCreate() {
    super.onCreate()
    initSqlDelightExperimentalDependencies()
  }
}