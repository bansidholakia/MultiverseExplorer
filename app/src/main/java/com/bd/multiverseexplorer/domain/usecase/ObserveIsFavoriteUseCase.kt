package com.bd.multiverseexplorer.domain.usecase

import com.bd.multiverseexplorer.domain.repository.FavoriteRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveIsFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    operator fun invoke(
        characterId: Int
    ): Flow<Boolean> {

        return repository.observeIsFavorite(
            characterId
        )
    }
}