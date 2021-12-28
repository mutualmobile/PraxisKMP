package com.baseio.kmm.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.sqljs.initSqlDriver
import kotlinx.coroutines.await

actual class DriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return initSqlDriver(BaseIoDB.Schema).await()
    }
}