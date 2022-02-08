package com.baseio.kmm.di

import com.baseio.kmm.data.local.GithubTrendingLocal
import com.baseio.kmm.data.local.GithubTrendingLocalImpl
import com.baseio.kmm.data.network.GithubTrendingAPI
import com.baseio.kmm.data.network.GithubTrendingAPIImpl
import com.baseio.kmm.domain.usecases.trendingrepos.FetchTrendingReposUseCase
import com.baseio.kmm.domain.usecases.trendingrepos.GetLocalReposUseCase
import com.baseio.kmm.domain.usecases.trendingrepos.SaveTrendingReposUseCase
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

fun initSharedDependencies() = startKoin {
    modules(commonModule, useCaseModule, platformModule())
}

fun initSqlDelightExperimentalDependencies() = startKoin {
    modules(jsModule, useCaseModule, platformModule())
}

val commonModule = module {
    single { httpClient(get()) }
    single<GithubTrendingLocal> { GithubTrendingLocalImpl(get()) }
    single<GithubTrendingAPI> { GithubTrendingAPIImpl(get()) }
}

val jsModule = module {
    single { httpClient(get()) }
    single<GithubTrendingLocal> { GithubTrendingLocalImpl() }
    single<GithubTrendingAPI> { GithubTrendingAPIImpl(get()) }
}

val useCaseModule = module {
    single { FetchTrendingReposUseCase(get()) }
    single { SaveTrendingReposUseCase(get()) }
    single { GetLocalReposUseCase(get()) }
}

class UseCasesComponent : KoinComponent {
    fun provideFetchTrendingReposUseCase(): FetchTrendingReposUseCase = get()
    fun provideSaveTrendingReposUseCase(): SaveTrendingReposUseCase = get()
    fun provideGetLocalReposUseCase(): GetLocalReposUseCase = get()
}

class SharedComponent : KoinComponent {
    fun provideGithubTrendingAPI(): GithubTrendingAPI = get()
    fun provideGithubTrendingLocal(): GithubTrendingLocal = get()
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
