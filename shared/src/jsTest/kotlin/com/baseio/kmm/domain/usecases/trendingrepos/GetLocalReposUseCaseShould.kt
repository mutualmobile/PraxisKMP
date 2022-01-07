package com.baseio.kmm.domain.usecases.trendingrepos

import com.baseio.kmm.domain.usecases.base.BaseTest
import com.baseio.kmm.domain.usecases.trendingrepos.utils.loadRequiredModules
import com.baseio.kmm.domain.usecases.utils.testItem
import com.baseio.kmm.domain.usecases.utils.testListOfItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertTrue

class GetLocalReposUseCaseShould : BaseTest() {

    @ExperimentalCoroutinesApi
    @Test
    fun return_flowOf_listOf_githubReposItem() {
        runTest {
            loadRequiredModules()
            val saveTrendingReposUseCase: SaveTrendingReposUseCase by inject()
            val getLocalReposUseCase: GetLocalReposUseCase by inject()
            saveTrendingReposUseCase.perform(testListOfItems)
            val listOfItemsFromFlow = getLocalReposUseCase.perform(null).first()
            assertTrue { listOfItemsFromFlow.isNotEmpty() }
            assertTrue { listOfItemsFromFlow.contains(testItem) }
        }
    }
}
