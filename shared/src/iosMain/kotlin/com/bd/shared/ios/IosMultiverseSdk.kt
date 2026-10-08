package com.bd.shared.ios

import com.bd.shared.core.createSharedContainer
import com.bd.shared.domain.model.AppResult
import com.bd.shared.domain.model.Character

class IosMultiverseSdk {

    private val container =
        createSharedContainer()

    suspend fun getCharacter(
        id: Int
    ): Character? {

        return when (
            val result =
                container
                    .getCharacterUseCase(
                        id
                    )
        ) {

            is AppResult.Success ->
                result.data

            is AppResult.Error ->
                null
        }
    }

    suspend fun toggleFavorite(
        character: Character,
        isFavorite: Boolean
    ) {

        container
            .toggleFavoriteUseCase(
                character = character,
                isCurrentlyFavorite = isFavorite
            )
    }
}