package com.bd.shared.data.repository

import com.bd.shared.data.local.database.getDatabaseBuilder
import com.bd.shared.domain.repository.FavoriteRepository

fun createFavoriteRepository():
        FavoriteRepository {

    return createFavoriteRepository(
        builder =
            getDatabaseBuilder()
    )
}