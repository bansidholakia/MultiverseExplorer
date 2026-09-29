package com.bd.multiverseexplorer.domain.usecase

import com.bd.multiverseexplorer.domain.repository.FavoriteRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import com.bd.multiverseexplorer.domain.model.Character

class ObserveFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    operator fun invoke():
            Flow<List<Character>> {

        return repository.observeFavorites()
    }
}