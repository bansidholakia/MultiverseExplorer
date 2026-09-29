package com.bd.multiverseexplorer.data.repository

import com.bd.multiverseexplorer.data.local.dao.FavoriteCharacterDao
import com.bd.multiverseexplorer.data.mapper.toCharacter
import com.bd.multiverseexplorer.data.mapper.toFavoriteEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.bd.multiverseexplorer.domain.model.Character
import com.bd.multiverseexplorer.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteCharacterDao
) : FavoriteRepository {

    override fun observeFavorites():
            Flow<List<Character>> {

        return dao
            .observeFavorites()
            .map { entities ->

                entities.map {
                    it.toCharacter()
                }
            }
    }

    override fun observeIsFavorite(
        characterId: Int
    ): Flow<Boolean> {

        return dao.observeIsFavorite(
            characterId
        )
    }

    override suspend fun addFavorite(
        character: Character
    ) {

        dao.upsertFavorite(
            character.toFavoriteEntity()
        )
    }

    override suspend fun removeFavorite(
        characterId: Int
    ) {

        dao.deleteFavorite(
            characterId
        )
    }
}