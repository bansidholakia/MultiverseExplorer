package com.bd.multiverseexplorer.ui.character.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd.multiverseexplorer.data.repository.CharacterRepository
import com.bd.multiverseexplorer.data.repository.FavoriteRepository
import com.bd.multiverseexplorer.navigation.CharacterDetailsRoute
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
    private val repository: CharacterRepository,
    private val favoriteRepository: FavoriteRepository
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

            try{

                val character = repository.getCharacter(route.characterId)



                _uiState.value = if(character != null){
                    val isFavorite =
                        favoriteRepository
                            .observeIsFavorite(
                                character.id
                            )
                            .first()
                    CharacterDetailsUiState.Success(
                        character, isFavorite
                    )
                }else{
                    CharacterDetailsUiState.Error(
                        errorMsg = "Character not found!"
                    )
                }
            }catch (e: Exception){
                _uiState.value = CharacterDetailsUiState.Error(errorMsg = e.message ?: "Something went wrong")
            }
        }
    }

    fun observeFavorite(){
        viewModelScope.launch {
            favoriteRepository
                .observeIsFavorite(
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

            if (currentState.isFavorite) {

                favoriteRepository
                    .removeFavorite(
                        currentState.character.id
                    )

            } else {

                favoriteRepository
                    .addFavorite(
                        currentState.character
                    )
            }
        }
    }

    @AssistedFactory
    interface Factory{
        fun create(
            route: CharacterDetailsRoute
        ) : CharacterDetailsViewModel
    }
}