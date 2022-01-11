package com.baseio.kmm.android

import android.app.Application
import com.baseio.kmm.db.DriverFactory
import com.baseio.kmm.di.SharedComponent
import com.baseio.kmm.di.UseCasesComponent
import com.baseio.kmm.di.initJSDependencies
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

val sharedComponent = SharedComponent()
val useCasesComponent = UseCasesComponent()

class PraxisApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initJSDependencies()
    }
}