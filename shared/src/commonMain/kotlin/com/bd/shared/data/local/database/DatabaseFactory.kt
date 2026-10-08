package com.bd.shared.data.local.database

import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal fun buildDatabase(
    builder:
    RoomDatabase.Builder<
            MultiverseDatabase
            >
): MultiverseDatabase {

    return builder
        .setDriver(
            BundledSQLiteDriver()
        )
        .setQueryCoroutineContext(
            Dispatchers.IO
        )
        .build()
}