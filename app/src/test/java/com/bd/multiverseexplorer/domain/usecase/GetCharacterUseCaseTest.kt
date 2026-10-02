package com.bd.multiverseexplorer.domain.usecase

import com.bd.shared.domain.model.AppError
import com.bd.shared.domain.model.AppResult
import com.bd.multiverseexplorer.fake.FakeCharacterRepository
import com.bd.multiverseexplorer.fake.testRick
import com.bd.shared.domain.usecase.GetCharacterUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCharacterUseCaseTest {

    private lateinit var repository: FakeCharacterRepository

    private lateinit var useCase: GetCharacterUseCase

    @Before
    fun setup(){
        repository =
            FakeCharacterRepository()

        useCase =
            GetCharacterUseCase(
                repository
            )

    }

    @Test
    fun `when character exists returns success` () =
        runTest {
            // Given

            repository.characterResult =
                AppResult.Success(
                    testRick
                )

            // When

            val result =
                useCase(
                    id = 1
                )

            // Then

            assertTrue(
                result is AppResult.Success
            )

            assertEquals(
                testRick,
                (result as AppResult.Success)
                    .data
            )
        }

    @Test
    fun `passes correct character id to repository`() =
        runTest {

            // Given

            repository.characterResult =
                AppResult.Success(
                    testRick
                )

            // When

            useCase(
                id = 42
            )

            // Then

            assertEquals(
                42,
                repository.requestedCharacterId
            )
        }

    @Test
    fun `when repository returns network error use case returns network error`() =
        runTest {

            // Given

            repository.characterResult =
                AppResult.Error(
                    AppError.Network
                )

            // When

            val result =
                useCase(
                    id = 1
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