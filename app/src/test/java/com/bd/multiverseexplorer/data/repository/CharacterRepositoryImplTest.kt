package com.bd.multiverseexplorer.data.repository

import com.bd.shared.domain.model.AppError
import com.bd.shared.domain.model.AppResult
import com.bd.multiverseexplorer.fake.FakeRickMortyApi
import com.bd.multiverseexplorer.fake.testCharacterDto
import com.bd.shared.data.repository.CharacterRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CharacterRepositoryImplTest {

    private lateinit var api:
            FakeRickMortyApi

    private lateinit var repository:
            CharacterRepositoryImpl

    @Before
    fun setup() {

        api =
            FakeRickMortyApi()

        repository =
            CharacterRepositoryImpl(
                api = api
            )
    }

    @Test
    fun `get character returns mapped domain character on success`() =
        runTest {

            // Given

            api.characterDto =
                testCharacterDto

            // When

            val result =
                repository.getCharacter(
                    id = 1
                )

            // Then

            assertTrue(
                result is AppResult.Success
            )

            val character =
                (result as AppResult.Success)
                    .data

            assertEquals(
                1,
                character.id
            )

            assertEquals(
                "Rick Sanchez",
                character.name
            )

            assertEquals(
                "Earth (C-137)",
                character.originName
            )
        }

    @Test
    fun `get character sends correct id to api`() =
        runTest {

            // When

            repository.getCharacter(
                id = 42
            )

            // Then

            assertEquals(
                42,
                api.requestedCharacterId
            )
        }

    @Test
    fun `io exception maps to network error`() =
        runTest {

            // Given

            api.characterException =
                java.io.IOException(
                    "No internet"
                )

            // When

            val result =
                repository.getCharacter(
                    1
                )

            // Then

            assertEquals(
                AppResult.Error(
                    AppError.Network
                ),
                result
            )
        }
}