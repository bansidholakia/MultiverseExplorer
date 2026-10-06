package com.bd.shared.data.local.database

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase

internal fun getDatabaseBuilder(
    context: Context
): RoomDatabase.Builder<
        MultiverseDatabase
        > {

    val appContext =
        context.applicationContext

    val dbFile =
        appContext.getDatabasePath(
            "multiverse.db"
        )

    return Room.databaseBuilder<
            MultiverseDatabase
            >(
        context = appContext,
        name = dbFile.absolutePath
    )
}