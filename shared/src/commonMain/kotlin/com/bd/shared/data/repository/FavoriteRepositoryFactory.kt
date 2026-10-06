package com.bd.shared.data.repository

import androidx.room3.RoomDatabase
import com.bd.shared.data.local.database.MultiverseDatabase
import com.bd.shared.data.local.database.buildDatabase
import com.bd.shared.domain.repository.FavoriteRepository

internal fun createFavoriteRepository(
    builder:
    RoomDatabase.Builder<
            MultiverseDatabase
            >
): FavoriteRepository {

    val database =
        buildDatabase(
            builder
        )

    return FavoriteRepositoryImpl(
        dao =
            database
                .favoriteCharacterDao()
    )
}