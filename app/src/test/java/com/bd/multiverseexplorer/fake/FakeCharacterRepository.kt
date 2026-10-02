package com.bd.multiverseexplorer.fake

import androidx.paging.PagingData
import com.bd.shared.domain.model.AppError
import com.bd.shared.domain.model.AppResult
import com.bd.shared.domain.model.Character
import com.bd.shared.domain.model.CharacterStatus
import com.bd.shared.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCharacterRepository : CharacterRepository {

    var characterResult:
            AppResult<Character> =
        AppResult.Error(
            AppError.NotFound
        )

    var requestedCharacterId: Int? =
        null

    var receivedSearchQuery:
            String? = null

    var receivedStatus:
            CharacterStatus? = null

    override fun getCharacters(
        searchQuery: String,
        status: CharacterStatus
    ): Flow<PagingData<Character>> {

        receivedSearchQuery =
            searchQuery

        receivedStatus =
            status

        return flowOf(
            PagingData.empty()
        )
    }

    override suspend fun getCharacter(id: Int?): AppResult<Character> {
        requestedCharacterId = id

        return characterResult
    }

}