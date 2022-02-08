package com.baseio.kmm.di

import com.baseio.kmm.db.DriverFactory
import io.ktor.client.engine.java.*
import org.koin.dsl.module

actual fun platformModule() = module {
    single { Java.create() }
    single {
        DriverFactory().createDriver()
    }
}

