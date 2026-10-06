package com.bd.shared.core

import android.content.Context
import com.bd.shared.data.repository.createCharacterRepository
import com.bd.shared.data.repository.createFavoriteRepository

fun createSharedContainer(
    context: Context
): SharedContainer {

    val characterRepository =
        createCharacterRepository()

    val favoriteRepository =
        createFavoriteRepository(
            context = context
        )

    return SharedContainer(
        characterRepository =
            characterRepository,
        favoriteRepository =
            favoriteRepository
    )
}