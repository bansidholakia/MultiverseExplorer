package com.bd.multiverseexplorer.presentation

import com.bd.multiverseexplorer.domain.model.AppError
import com.bd.multiverseexplorer.domain.model.AppResult
import com.bd.multiverseexplorer.domain.usecase.GetCharacterUseCase
import com.bd.multiverseexplorer.domain.usecase.ObserveIsFavoriteUseCase
import com.bd.multiverseexplorer.domain.usecase.ToggleFavoriteUseCase
import com.bd.multiverseexplorer.fake.FakeCharacterRepository
import com.bd.multiverseexplorer.fake.FakeFavoriteRepository
import com.bd.multiverseexplorer.fake.testRick
import com.bd.multiverseexplorer.navigation.CharacterDetailsRoute
import com.bd.multiverseexplorer.presentation.character.detail.CharacterDetailsUiState
import com.bd.multiverseexplorer.presentation.character.detail.CharacterDetailsViewModel
import com.bd.multiverseexplorer.testutil.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule =
        MainDispatcherRule()

    private lateinit var characterRepository:
            FakeCharacterRepository

    private lateinit var favoriteRepository:
            FakeFavoriteRepository

    private lateinit var getCharacterUseCase:
            GetCharacterUseCase

    private lateinit var observeIsFavoriteUseCase:
            ObserveIsFavoriteUseCase

    private lateinit var toggleFavoriteUseCase:
            ToggleFavoriteUseCase

    @Before
    fun setup() {

        characterRepository =
            FakeCharacterRepository()

        favoriteRepository =
            FakeFavoriteRepository()

        getCharacterUseCase =
            GetCharacterUseCase(
                characterRepository
            )

        observeIsFavoriteUseCase =
            ObserveIsFavoriteUseCase(
                favoriteRepository
            )

        toggleFavoriteUseCase =
            ToggleFavoriteUseCase(
                favoriteRepository
            )
    }

    @Test
    fun `character loads successfully`() =
        runTest {

            // Given

            characterRepository
                .characterResult =
                AppResult.Success(
                    testRick
                )

            favoriteRepository
                .favoriteState
                .value = false

            // When

            val viewModel =
                createViewModel()

            // Then - before coroutine executes

            assertEquals(
                CharacterDetailsUiState.Loading,
                viewModel.uiState.value
            )

            advanceUntilIdle()

            // Then - after loading

            assertEquals(
                CharacterDetailsUiState.Success(
                    character = testRick,
                    isFavorite = false
                ),
                viewModel.uiState.value
            )
        }

    @Test
    fun `network error shows correct error state`() =
        runTest {

            // Given

            characterRepository
                .characterResult =
                AppResult.Error(
                    AppError.Network
                )

            // When

            val viewModel =
                createViewModel()

            advanceUntilIdle()

            // Then

            assertEquals(
                CharacterDetailsUiState.Error(
                    errorMsg =
                        "No internet connection"
                ),
                viewModel.uiState.value
            )
        }

    @Test
    fun `favorite character loads as favorite`() =
        runTest {

            // Given

            characterRepository
                .characterResult =
                AppResult.Success(
                    testRick
                )

            favoriteRepository
                .favoriteState
                .value = true

            // When

            val viewModel =
                createViewModel()

            advanceUntilIdle()

            // Then

            assertEquals(
                CharacterDetailsUiState.Success(
                    character = testRick,
                    isFavorite = true
                ),
                viewModel.uiState.value
            )
        }

    @Test
    fun `favorite state changes when repository emits new value`() =
        runTest {

            // Given

            characterRepository
                .characterResult =
                AppResult.Success(
                    testRick
                )

            favoriteRepository
                .favoriteState
                .value = false

            val viewModel =
                createViewModel()

            advanceUntilIdle()

            assertEquals(
                false,
                (
                        viewModel.uiState.value
                                as CharacterDetailsUiState.Success
                        ).isFavorite
            )

            // When

            favoriteRepository
                .favoriteState
                .value = true

            advanceUntilIdle()

            // Then

            assertEquals(
                true,
                (
                        viewModel.uiState.value
                                as CharacterDetailsUiState.Success
                        ).isFavorite
            )
        }

    @Test
    fun `toggle favorite adds character when not favorite`() =
        runTest {

            // Given

            characterRepository
                .characterResult =
                AppResult.Success(
                    testRick
                )

            favoriteRepository
                .favoriteState
                .value = false

            val viewModel =
                createViewModel()

            advanceUntilIdle()

            // When

            viewModel.toggleFavorite()

            advanceUntilIdle()

            // Then

            assertEquals(
                testRick,
                favoriteRepository
                    .addedCharacter
            )

            val state =
                viewModel.uiState.value
                        as CharacterDetailsUiState.Success

            assertEquals(
                true,
                state.isFavorite
            )
        }

    private fun createViewModel():
            CharacterDetailsViewModel {

        return CharacterDetailsViewModel(
            route =
                CharacterDetailsRoute(
                    characterId = 1
                ),

            getCharacterUseCase =
                getCharacterUseCase,

            observeIsFavorite =
                observeIsFavoriteUseCase,

            toggleFavoriteUseCase =
                toggleFavoriteUseCase
        )
    }
}
