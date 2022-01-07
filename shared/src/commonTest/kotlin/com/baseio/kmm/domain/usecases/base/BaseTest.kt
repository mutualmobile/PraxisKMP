package com.baseio.kmm.domain.usecases.base

import com.baseio.kmm.domain.usecases.utils.TrendingReposListSuccessResponse
import com.baseio.kmm.data.network.GithubTrendingAPIImpl
import com.baseio.kmm.domain.usecases.trendingrepos.FetchTrendingReposUseCase
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
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseTest : KoinTest {

    @BeforeTest
    fun setUp() {
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
                        Json {
                            ignoreUnknownKeys = true
                        }
                    )
                    acceptContentTypes = acceptContentTypes + ContentType.Any
                }
            }
        )

        startKoin {
            modules(
                module {
                    single { FetchTrendingReposUseCase(api) }
                }
            )
        }
    }

    @AfterTest
    fun teardown() {
        stopKoin()
    }
}
