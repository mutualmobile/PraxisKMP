package com.baseio.kmm.di

import com.baseio.kmm.data.network.GithubTrendingAPI
import com.baseio.kmm.data.network.GithubTrendingAPIImpl
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.core.component.get

expect fun platformModule(): Module

fun initIosDependencies() = startKoin {
    modules(commonModule, platformModule())
}

val commonModule = module {
    single { httpClient(get()) }
    single<GithubTrendingAPI> { GithubTrendingAPIImpl(get()) }
}

class IosComponent : KoinComponent {
    fun provideGithubTrendingAPI(): GithubTrendingAPI = get()
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
