package com.bd.multiverseexplorer.domain.usecase

import com.bd.shared.domain.model.CharacterStatus
import com.bd.multiverseexplorer.fake.FakeCharacterRepository
import com.bd.shared.domain.usecase.GetCharactersUseCase
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    private lateinit var repository:
            FakeCharacterRepository

    private lateinit var useCase:
            GetCharactersUseCase

    @Before
    fun setup() {

        repository =
            FakeCharacterRepository()

        useCase =
            GetCharactersUseCase(
                repository
            )
    }

    @Test
    fun `search query is trimmed before repository call`() {
        // Given

        val query =
            "   Rick   "

        // When

        useCase(
            searchQuery = query,
            status =
                CharacterStatus.ALIVE
        )

        // Then

        assertEquals(
            "Rick",
            repository.receivedSearchQuery
        )

        assertEquals(
            CharacterStatus.ALIVE,
            repository.receivedStatus
        )
    }
}