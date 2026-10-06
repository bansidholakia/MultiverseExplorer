package com.bd.shared.core

import com.bd.shared.data.repository.createCharacterRepository
import com.bd.shared.data.repository.createFavoriteRepository

fun createSharedContainer():
        SharedContainer {

    val characterRepository =
        createCharacterRepository()

    val favoriteRepository =
        createFavoriteRepository()

    return SharedContainer(
        characterRepository =
            characterRepository,
        favoriteRepository =
            favoriteRepository
    )
}