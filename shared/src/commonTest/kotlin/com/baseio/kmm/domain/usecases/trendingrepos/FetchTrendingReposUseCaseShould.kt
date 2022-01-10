package com.baseio.kmm.domain.usecases.trendingrepos

import com.baseio.kmm.domain.usecases.base.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertTrue

class FetchTrendingReposUseCaseShould : BaseTest() {
    private val fetchTrendingReposUseCase: FetchTrendingReposUseCase by inject()

    @ExperimentalCoroutinesApi
    @Test
    fun return_listOf_GithubReposItem() {
        runTest {
            assertTrue { fetchTrendingReposUseCase.perform("Kotlin").isNotEmpty() }
        }
    }
}
