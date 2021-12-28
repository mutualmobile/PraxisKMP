package com.baseio.kmm.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.sqljs.initSqlDriver
import kotlinx.coroutines.await

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        TODO("Fix this! Koin can not init suspended method, find another way of providing sql driver")
        return initSqlDriver(BaseIoDB.Schema).await()
    }
}