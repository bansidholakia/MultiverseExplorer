package com.bd.shared.data.repository

import android.content.Context
import com.bd.shared.data.local.database.getDatabaseBuilder
import com.bd.shared.domain.repository.FavoriteRepository

fun createFavoriteRepository(
    context: Context
): FavoriteRepository {

    return createFavoriteRepository(
        builder =
            getDatabaseBuilder(
                context
            )
    )
}