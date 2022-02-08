package com.baseio.kmm.composedesktop

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window

fun main() = application {
  val windowState  = rememberWindowState(width = 300.dp, height = 300.dp)

  Window(
    onCloseRequest = ::exitApplication,
    title = "Compose for Desktop",
    state = windowState
  ) {
    val count = remember { mutableStateOf(0) }
    MaterialTheme {
      Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
          onClick = {
            count.value++
          }) {
          Text(if (count.value == 0) "Hello World" else "Clicked ${count.value}!")
        }
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
          onClick = {
            count.value = 0
          }) {
          Text("Reset")
        }
      }
    }
  }
}