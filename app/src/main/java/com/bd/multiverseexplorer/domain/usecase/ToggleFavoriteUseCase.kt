package com.bd.multiverseexplorer.domain.usecase

import com.bd.multiverseexplorer.domain.repository.FavoriteRepository
import jakarta.inject.Inject
import com.bd.multiverseexplorer.domain.model.Character

class ToggleFavoriteUseCase @Inject constructor(
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