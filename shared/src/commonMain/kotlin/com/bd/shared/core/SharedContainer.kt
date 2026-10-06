package com.bd.shared.core

import com.bd.shared.domain.repository.CharacterRepository
import com.bd.shared.domain.repository.FavoriteRepository
import com.bd.shared.domain.usecase.GetCharacterUseCase
import com.bd.shared.domain.usecase.GetCharactersUseCase
import com.bd.shared.domain.usecase.ObserveFavoritesUseCase
import com.bd.shared.domain.usecase.ObserveIsFavoriteUseCase
import com.bd.shared.domain.usecase.ToggleFavoriteUseCase

class SharedContainer internal constructor(
    characterRepository: CharacterRepository,
    favoriteRepository: FavoriteRepository
) {

    val getCharacterUseCase =
        GetCharacterUseCase(
            characterRepository
        )

    val getCharactersUseCase =
        GetCharactersUseCase(
            characterRepository
        )

    val observeFavoritesUseCase =
        ObserveFavoritesUseCase(
            favoriteRepository
        )

    val observeIsFavoriteUseCase =
        ObserveIsFavoriteUseCase(
            favoriteRepository
        )

    val toggleFavoriteUseCase =
        ToggleFavoriteUseCase(
            favoriteRepository
        )
}