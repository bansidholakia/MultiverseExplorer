package com.bd.multiverseexplorer.fake

import com.bd.multiverseexplorer.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import com.bd.multiverseexplorer.domain.model.Character

class FakeFavoriteRepository :
    FavoriteRepository {

    val favorites =
        mutableListOf<Character>()

    val favoriteState =
        MutableStateFlow(false)

    var addedCharacter:
            Character? = null

    var removedCharacterId:
            Int? = null

    override fun observeFavorites():
            Flow<List<Character>> {

        return MutableStateFlow(
            favorites.toList()
        )
    }

    override fun observeIsFavorite(
        characterId: Int
    ): Flow<Boolean> {

        return favoriteState
    }

    override suspend fun addFavorite(
        character: Character
    ) {

        addedCharacter =
            character

        favorites.add(
            character
        )

        favoriteState.value =
            true
    }

    override suspend fun removeFavorite(
        characterId: Int
    ) {

        removedCharacterId =
            characterId

        favorites.removeAll {
            it.id == characterId
        }

        favoriteState.value =
            false
    }
}