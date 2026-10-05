package com.bd.shared.data.remote.api

import com.bd.shared.data.remote.dto.CharacterDto
import com.bd.shared.data.remote.dto.CharacterResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class RickMortyApi(
    private val client: HttpClient
) {

    private companion object {

        const val BASE_URL =
            "https://rickandmortyapi.com/api"
    }

    suspend fun getCharacters(
        page: Int,
        name: String? = null,
        status: String? = null
    ): CharacterResponseDto {

        return client
            .get("$BASE_URL/character") {

                parameter(
                    "page",
                    page
                )

                if (!name.isNullOrBlank()) {

                    parameter(
                        "name",
                        name
                    )
                }

                if (!status.isNullOrBlank()) {

                    parameter(
                        "status",
                        status
                    )
                }
            }
            .body()
    }

    suspend fun getCharacter(
        id: Int?
    ): CharacterDto {

        return client
            .get(
                "$BASE_URL/character/$id"
            )
            .body()
    }
}