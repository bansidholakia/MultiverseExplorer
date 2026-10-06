package com.bd.shared.data.repository

import com.bd.shared.data.local.dao.FavoriteCharacterDao
import com.bd.shared.data.mapper.toCharacter
import com.bd.shared.data.mapper.toFavoriteEntity
import com.bd.shared.domain.model.Character
import com.bd.shared.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoriteRepositoryImpl(
    private val dao:
    FavoriteCharacterDao
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