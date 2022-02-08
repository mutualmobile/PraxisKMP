package com.baseio.kmm.android

import android.app.Application
import com.baseio.kmm.db.DriverFactory
import com.baseio.kmm.di.SharedComponent
import com.baseio.kmm.di.UseCasesComponent
import com.baseio.kmm.di.initSqlDelightExperimentalDependencies
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

val sharedComponent = SharedComponent()
val useCasesComponent = UseCasesComponent()

class PraxisApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initSqlDelightExperimentalDependencies()
        GlobalScope.launch {
            precheckSqlite()
        }
    }

    private suspend fun precheckSqlite() {
        if (sharedComponent.provideGithubTrendingLocal().driver == null) {
            val driver = DriverFactory(context = this).createDriverBlocking()
            sharedComponent.provideGithubTrendingLocal().driver = driver
        }
    }
}