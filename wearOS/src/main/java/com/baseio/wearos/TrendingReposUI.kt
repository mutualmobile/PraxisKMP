package com.baseio.wearos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.baseio.wearos.ui.theme.BaseiOKMMTheme

class TrendingReposUI : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val viewModel = TrendingReposVM(application)
    setContent {
      BaseiOKMMTheme {
        val materialBlue700 = Color(0xFF1976D2)
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          Scaffold(
            content = {
                      TrendingReposListScreen(viewModel)
            },
          )
        }
      }
    }
  }
}

