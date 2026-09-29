package com.bd.multiverseexplorer.data.repository

import androidx.paging.PagingData
import com.bd.multiverseexplorer.data.fakeCharacterData
import com.bd.multiverseexplorer.model.Character
import com.bd.multiverseexplorer.model.CharacterStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.time.Duration.Companion.milliseconds

class FakeCharacterRepository : CharacterRepository {
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

    override suspend fun getCharacter(id: Int?): Character? {
        delay(500.milliseconds)

        return fakeCharacterData.firstOrNull {
            it.id == id
        }
    }
}