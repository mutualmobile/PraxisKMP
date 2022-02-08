package com.baseio.kmm.di

import com.baseio.kmm.db.DriverFactory
import org.koin.dsl.module
import io.ktor.client.engine.js.*

actual fun platformModule() = module {
  single { Js.create() }
  single {
    DriverFactory().createDriver() // this never gets called for js since sqldriver is async and koin doesn't support that yet.
  }
}

