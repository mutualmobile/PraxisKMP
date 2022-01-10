package com.baseio.kmm.db

import co.touchlab.sqliter.DatabaseConfiguration
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(BaseIoDB.Schema, "baseio.db")
    }

    actual suspend fun createDriverBlocking(): SqlDriver {
        return NativeSqliteDriver(BaseIoDB.Schema, "baseio.db")
    }

    fun createInMemoryDriver(): SqlDriver {
        return NativeSqliteDriver(
            DatabaseConfiguration(
                inMemory = true,
                name = "baseio.db",
                create = { connection ->
                    wrapConnection(connection) { driver -> BaseIoDB.Schema.create(driver) }
                },
                version = BaseIoDB.Schema.version,
            )
        )
    }
}
