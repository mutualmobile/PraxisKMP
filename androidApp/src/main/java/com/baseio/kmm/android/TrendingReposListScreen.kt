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
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.baseio.kmm.features.trending.GithubTrendingDataModel
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun TrendingReposListScreen(viewModel: TrendingReposVM) {
  Column {
    val uiState by viewModel.uiState.collectAsState()
    if (uiState is GithubTrendingDataModel.LoadingState) {
      CircularProgressIndicator()
    }

    AnimatedVisibility(visible = uiState is GithubTrendingDataModel.ErrorState) {
      Text(text = (uiState as GithubTrendingDataModel.ErrorState).throwable.message ?:"Error", modifier = Modifier.padding(4.dp))
    }

    if(uiState is GithubTrendingDataModel.SuccessState){
      LazyColumn {
        (uiState as GithubTrendingDataModel.SuccessState).trendingList.forEach {
          item {
            Row(modifier = Modifier
              .fillMaxWidth()
              .padding(4.dp)) {
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
}