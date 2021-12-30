package com.baseio.kmm.di

import com.baseio.kmm.db.DriverFactory
import org.koin.dsl.module
import io.ktor.client.engine.android.*

actual fun platformModule() = module {
    single { Android.create() }
    single {
        DriverFactory(get()).createDriver()
    }
}

