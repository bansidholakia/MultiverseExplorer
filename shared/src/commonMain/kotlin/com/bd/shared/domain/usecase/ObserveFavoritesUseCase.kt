package com.bd.shared.domain.usecase

import com.bd.shared.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import com.bd.shared.domain.model.Character

class ObserveFavoritesUseCase(
    private val repository: FavoriteRepository
) {

    operator fun invoke():
            Flow<List<Character>> {

        return repository.observeFavorites()
    }
}