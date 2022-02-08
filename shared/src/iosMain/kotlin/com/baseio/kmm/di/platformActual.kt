package com.baseio.kmm.di

import com.baseio.kmm.db.DriverFactory
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module
import io.ktor.client.engine.ios.*

actual fun platformModule() = module {
    single { Ios.create() }
    single {
        DriverFactory().createDriver()
    }
}

