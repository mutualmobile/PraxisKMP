package com.baseio.kmm.android.base

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.baseio.kmm.android.utils.TrendingReposListSuccessResponse
import com.baseio.kmm.data.local.GithubTrendingLocal
import com.baseio.kmm.data.local.GithubTrendingLocalImpl
import com.baseio.kmm.data.network.GithubTrendingAPI
import com.baseio.kmm.data.network.GithubTrendingAPIImpl
import com.baseio.kmm.db.DriverFactory
import com.baseio.kmm.di.commonModule
import com.baseio.kmm.di.jsModule
import com.baseio.kmm.di.platformModule
import com.baseio.kmm.di.useCaseModule
import com.baseio.kmm.domain.usecases.trendingrepos.FetchTrendingReposUseCase
import com.baseio.kmm.domain.usecases.trendingrepos.GetLocalReposUseCase
import com.baseio.kmm.domain.usecases.trendingrepos.SaveTrendingReposUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
abstract class BaseTest : AutoCloseKoinTest() {
    @Before
    fun setup() {
        val api = GithubTrendingAPIImpl(
            HttpClient(
                MockEngine {
                    respond(
                        content = ByteReadChannel(TrendingReposListSuccessResponse.trimIndent()),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "plain/text")
                    )
                }
            ) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(
                        kotlinx.serialization.json.Json {
                            ignoreUnknownKeys = true
                        }
                    )
                    acceptContentTypes = acceptContentTypes + ContentType.Any
                }
            }
        )

        unloadKoinModules(
            listOf(
                jsModule,
                platformModule(),
                commonModule,
                useCaseModule,
                module { single<GithubTrendingAPI> { GithubTrendingAPIImpl(get()) } }
            )
        )
        loadKoinModules(
            module {
                single<GithubTrendingLocal> {
                    GithubTrendingLocalImpl(
                        DriverFactory(androidContext()).createInMemoryDriver()
                    )
                }
                single { FetchTrendingReposUseCase(api) }
                single { SaveTrendingReposUseCase(get()) }
                single { GetLocalReposUseCase(get()) }
            }
        )
    }
}
