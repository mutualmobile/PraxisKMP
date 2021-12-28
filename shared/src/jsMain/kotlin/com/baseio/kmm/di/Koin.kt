package com.baseio.kmm.di

import org.koin.dsl.module
import io.ktor.client.engine.js.*

actual fun platformModule() = module {
    single { Js.create() }
}

