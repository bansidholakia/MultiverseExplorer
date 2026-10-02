package com.bd.shared.domain.usecase

import com.bd.shared.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class ObserveIsFavoriteUseCase(
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