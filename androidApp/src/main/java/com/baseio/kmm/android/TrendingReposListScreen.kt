package com.baseio.kmm.android

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun TrendingReposListScreen(viewModel: TrendingReposVM) {
  Column {
    val uiState = viewModel.uiState

    if (uiState.loading) {
      CircularProgressIndicator()
    }

    AnimatedVisibility(visible = uiState.message.isNotBlank()) {
      Text(text = uiState.message, modifier = Modifier.padding(4.dp))
    }

    LazyColumn {
      uiState.dataToDisplayOnScreen.forEach {
        item {
          Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
            Image(
              painter = rememberImagePainter(it.avatar),
              contentDescription = null,
              modifier = Modifier.size(64.dp)
            )
            Column(modifier = Modifier.padding(all = 8.dp)) {
              Text(text = it.author ?: "author", style = TextStyle(fontWeight = FontWeight.Bold))
              Text(text = it.name ?: "name")
            }
          }
        }
      }
      item {
        Spacer(modifier = Modifier.navigationBarsPadding())
      }
    }

  }
}