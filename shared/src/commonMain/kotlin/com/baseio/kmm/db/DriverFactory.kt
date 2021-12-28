package com.baseio.kmm.db

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    suspend fun createDriver(): SqlDriver
}
