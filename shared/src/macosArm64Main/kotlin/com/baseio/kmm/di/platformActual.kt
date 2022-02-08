package com.baseio.kmm.di

import com.baseio.kmm.db.DriverFactory
import org.koin.dsl.module
import io.ktor.client.engine.ios.*

actual fun platformModule() = module {
  single { Ios.create() }
  single {
    DriverFactory().createDriver()
  }
}

