package com.bd.multiverseexplorer.presentation.character.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd.shared.domain.model.AppResult
import com.bd.shared.domain.usecase.GetCharacterUseCase
import com.bd.shared.domain.usecase.ObserveIsFavoriteUseCase
import com.bd.shared.domain.usecase.ToggleFavoriteUseCase
import com.bd.multiverseexplorer.navigation.CharacterDetailsRoute
import com.bd.multiverseexplorer.presentation.common.toMessage
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(
    assistedFactory =
        CharacterDetailsViewModel.Factory::class
)
class CharacterDetailsViewModel @AssistedInject constructor(
    @Assisted
    private val route: CharacterDetailsRoute,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val observeIsFavorite: ObserveIsFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel(){

    private val _uiState = MutableStateFlow<CharacterDetailsUiState>(CharacterDetailsUiState.Loading)
    val uiState : StateFlow<CharacterDetailsUiState> = _uiState.asStateFlow()

    init {
        loadCharacter()

        observeFavorite()
    }

    fun loadCharacter(){

        viewModelScope.launch {
            _uiState.value = CharacterDetailsUiState.Loading

            when (
                val result =
                    getCharacterUseCase(
                        route.characterId
                    )
            ) {

                is AppResult.Success -> {

                    val character =
                        result.data

                    val isFavorite =
                        observeIsFavorite(
                            character.id
                        ).first()

                    _uiState.value =
                        CharacterDetailsUiState.Success(
                            character = character,
                            isFavorite = isFavorite
                        )
                }

                is AppResult.Error -> {

                    _uiState.value =
                        CharacterDetailsUiState.Error(
                            errorMsg =
                                result.error
                                    .toMessage()
                        )
                }
            }
        }
    }

    fun observeFavorite(){
        viewModelScope.launch {
            observeIsFavorite(
                    route.characterId
                )
                .collect { isFavorite ->

                    _uiState.update {
                            currentState ->

                        if (
                            currentState
                                    is CharacterDetailsUiState.Success
                        ) {

                            currentState.copy(
                                isFavorite = isFavorite
                            )

                        } else {

                            currentState
                        }
                    }
                }
        }
    }

    fun toggleFavorite() {

        val currentState =
            _uiState.value

        if (
            currentState
                    !is CharacterDetailsUiState.Success
        ) {
            return
        }

        viewModelScope.launch {

            toggleFavoriteUseCase(
                character = currentState.character,
                isCurrentlyFavorite =
                    currentState.isFavorite
            )
        }
    }

    @AssistedFactory
    interface Factory{
        fun create(
            route: CharacterDetailsRoute
        ) : CharacterDetailsViewModel
    }
}