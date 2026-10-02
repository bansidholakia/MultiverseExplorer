package com.bd.shared.domain.repository

import com.bd.shared.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun observeFavorites() : Flow<List<Character>>

    fun observeIsFavorite(
        characterId: Int
    ): Flow<Boolean>

    suspend fun addFavorite(
        character: Character
    )

    suspend fun removeFavorite(
        characterId: Int
    )
}