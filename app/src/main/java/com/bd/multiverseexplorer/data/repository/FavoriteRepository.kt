package com.bd.multiverseexplorer.data.repository

import com.bd.multiverseexplorer.model.Character
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