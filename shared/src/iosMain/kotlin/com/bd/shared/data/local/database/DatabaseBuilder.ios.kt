package com.bd.shared.data.local.database

import androidx.room3.Room
import androidx.room3.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

internal fun getDatabaseBuilder():
        RoomDatabase.Builder<
                MultiverseDatabase
                > {

    val dbPath =
        documentDirectory() +
                "/multiverse.db"

    return Room.databaseBuilder<
            MultiverseDatabase
            >(
        name = dbPath
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory():
        String {

    val directory =
        NSFileManager
            .defaultManager
            .URLForDirectory(
                directory =
                    NSDocumentDirectory,
                inDomain =
                    NSUserDomainMask,
                appropriateForURL =
                    null,
                create = false,
                error = null
            )

    return requireNotNull(
        directory?.path
    )
}