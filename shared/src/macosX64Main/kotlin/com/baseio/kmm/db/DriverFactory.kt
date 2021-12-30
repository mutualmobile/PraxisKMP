package com.baseio.kmm.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(BaseIoDB.Schema, "baseio.db")
    }

    actual suspend fun createDriverBlocking(): SqlDriver {
        return NativeSqliteDriver(BaseIoDB.Schema, "baseio.db")
    }
}