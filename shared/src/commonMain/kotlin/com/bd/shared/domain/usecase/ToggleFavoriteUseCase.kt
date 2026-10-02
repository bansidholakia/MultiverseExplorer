package com.bd.shared.domain.usecase

import com.bd.shared.domain.repository.FavoriteRepository
import com.bd.shared.domain.model.Character

class ToggleFavoriteUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(
        character: Character,
        isCurrentlyFavorite: Boolean
    ) {

        if (isCurrentlyFavorite) {

            repository.removeFavorite(
                character.id
            )

        } else {

            repository.addFavorite(
                character
            )
        }
    }
}