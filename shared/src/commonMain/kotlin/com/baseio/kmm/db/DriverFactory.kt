package com.baseio.kmm.db

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
  fun createDriver(): SqlDriver
  suspend fun createDriverBlocking(): SqlDriver
}
