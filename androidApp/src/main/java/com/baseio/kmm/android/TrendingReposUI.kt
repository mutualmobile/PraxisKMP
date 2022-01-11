package com.baseio.kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.baseio.kmm.android.ui.theme.BaseiOAppTheme
import com.baseio.kmm.android.ui.theme.BaseiOKMMTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class TrendingReposUI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val viewModel = TrendingReposVM(application)
        setContent {
            BaseiOAppTheme {
                ProvideWindowInsets {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        val systemUiController = rememberSystemUiController()
                        val useDarkIcons = isSystemInDarkTheme()

                        SideEffect {
                            systemUiController.setSystemBarsColor(
                                color = Color.Transparent,
                                darkIcons = !useDarkIcons
                            )
                        }
                        Scaffold(
                            topBar = {
                                Surface(
                                    tonalElevation = 4.dp,
                                ) {
                                    SmallTopAppBar(
                                        title = { Text(text = "Trending Kotlin Repositories") },
                                        modifier = Modifier.statusBarsPadding()
                                    )
                                }
                            },
                            content = {
                                TrendingReposListScreen(viewModel)
                            },
                            backgroundColor = Color.Transparent
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseiOKMMTheme {
        Greeting("Android")
    }
}
