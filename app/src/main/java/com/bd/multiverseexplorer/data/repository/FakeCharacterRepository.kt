package com.bd.multiverseexplorer.data.repository

import androidx.paging.PagingData
import com.bd.multiverseexplorer.data.fakeCharacterData
import com.bd.multiverseexplorer.domain.model.AppError
import com.bd.multiverseexplorer.domain.model.AppResult
import com.bd.multiverseexplorer.domain.model.Character
import com.bd.multiverseexplorer.domain.model.CharacterStatus
import com.bd.multiverseexplorer.domain.repository.CharacterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.time.Duration.Companion.milliseconds

class FakeCharacterRepository : CharacterRepository {

    var characterResult:
            AppResult<Character> =
        AppResult.Error(
            AppError.NotFound
        )

    override suspend fun getCharacters(
        searchQuery: String,
        status: CharacterStatus
    ): Flow<PagingData<Character>> {
        val filtered =
            fakeCharacterData.filter { character ->

                val matchesSearch =
                    character.name.contains(
                        searchQuery,
                        ignoreCase = true
                    )

                val matchesStatus =
                    status == CharacterStatus.ALL ||
                            character.status.equals(
                                status.name,
                                ignoreCase = true
                            )

                matchesSearch && matchesStatus
            }

        return flowOf(
            PagingData.from(filtered)
        )
    }

    override suspend fun getCharacter(id: Int?): AppResult<Character> {
        delay(500.milliseconds)

        return characterResult
    }
}