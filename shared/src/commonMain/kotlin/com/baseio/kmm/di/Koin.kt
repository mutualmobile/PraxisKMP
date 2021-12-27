package com.baseio.kmm.di

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import com.squareup.sqldelight.db.SqlDriver

expect fun platformModule(): Module

fun initIosDependencies() = startKoin {
    modules(commonModule, platformModule())
}

val commonModule = module {
    single { httpClient(get()) }
}

class IosComponent : KoinComponent {
}

private fun httpClient(httpClientEngine: HttpClientEngine) = HttpClient(httpClientEngine) {
    val json = kotlinx.serialization.json.Json { isLenient = true; ignoreUnknownKeys = true }

    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}
