package com.bd.multiverseexplorer.fake

import com.bd.multiverseexplorer.data.remote.api.RickMortyApi
import com.bd.shared.data.remote.dto.CharacterDto
import com.bd.shared.data.remote.dto.CharacterResponseDto
import com.bd.shared.data.remote.dto.PageInfoDto

class FakeRickMortyApi : RickMortyApi {

    var characterDto:
            CharacterDto = testCharacterDto

    var characterException:
            Throwable? = null

    var requestedCharacterId:
            Int? = null

    override suspend fun getCharacter(
        id: Int?
    ): CharacterDto {

        requestedCharacterId = id

        characterException?.let {
            throw it
        }

        return characterDto
    }

    override suspend fun getCharacters(
        page: Int,
        name: String?,
        status: String?
    ): CharacterResponseDto {

        return CharacterResponseDto(
            info = PageInfoDto(
                count = 0,
                pages = 0,
                next = null,
                prev = null
            ),
            results = emptyList()
        )
    }
}