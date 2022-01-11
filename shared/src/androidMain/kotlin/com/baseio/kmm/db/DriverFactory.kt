package com.baseio.kmm.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(BaseIoDB.Schema, context, "baseio.db")
    }

    actual suspend fun createDriverBlocking(): SqlDriver {
        return AndroidSqliteDriver(BaseIoDB.Schema, context, "baseio.db")
    }
}