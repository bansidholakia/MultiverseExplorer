package com.bd.multiverseexplorer.domain.usecase

import com.bd.multiverseexplorer.fake.FakeFavoriteRepository
import com.bd.multiverseexplorer.fake.testRick
import com.bd.shared.domain.usecase.ToggleFavoriteUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ToggleFavoriteUseCaseTest {

    private lateinit var repository:
            FakeFavoriteRepository

    private lateinit var useCase:
            ToggleFavoriteUseCase

    @Before
    fun setup() {

        repository =
            FakeFavoriteRepository()

        useCase =
            ToggleFavoriteUseCase(
                repository
            )
    }

    @Test
    fun `when character is not favorite it is added`() =
        runTest {

            // Given

            val isFavorite = false

            // When

            useCase(
                character = testRick,
                isCurrentlyFavorite =
                    isFavorite
            )

            // Then

            assertEquals(
                testRick,
                repository.addedCharacter
            )

            assertNull(
                repository.removedCharacterId
            )
        }

    @Test
    fun `when character is already favorite it is removed`() =
        runTest {

            // Given

            val isFavorite = true

            // When

            useCase(
                character = testRick,
                isCurrentlyFavorite =
                    isFavorite
            )

            // Then

            assertEquals(
                testRick.id,
                repository.removedCharacterId
            )

            assertNull(
                repository.addedCharacter
            )
        }
}