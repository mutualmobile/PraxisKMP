package com.baseio.kmm.android.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.baseio.kmm.android.base.BaseTest
import com.baseio.wearos.TrendingReposListScreen
import com.baseio.wearos.TrendingReposVM
import org.junit.Rule
import org.junit.Test
import org.koin.test.get

class TrendingReposListScreenTest : BaseTest() {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun items_displayed_correctly() {
        val trendingReposVM = TrendingReposVM(get())
        with(composeTestRule) {
            setContent { TrendingReposListScreen(viewModel = trendingReposVM) }
            onNodeWithTag(TrendingReposListScreen.RepoColumn).assertIsDisplayed()
        }
    }
}
