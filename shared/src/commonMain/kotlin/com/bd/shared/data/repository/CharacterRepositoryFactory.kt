package com.bd.shared.data.repository

import com.bd.shared.data.remote.api.RickMortyApi
import com.bd.shared.data.remote.createHttpClient
import com.bd.shared.domain.repository.CharacterRepository

fun createCharacterRepository(): CharacterRepository {

    val client =
        createHttpClient()

    val api =
        RickMortyApi(
            client = client
        )

    return CharacterRepositoryImpl(
        api = api
    )
}