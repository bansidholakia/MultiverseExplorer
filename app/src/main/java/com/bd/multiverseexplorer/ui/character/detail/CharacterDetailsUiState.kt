package com.bd.multiverseexplorer.ui.character.detail

import com.bd.multiverseexplorer.model.Character

sealed interface CharacterDetailsUiState{
    data object Loading : CharacterDetailsUiState

    data class Success (
        val character :  Character,
        val isFavorite: Boolean
    ) : CharacterDetailsUiState

    data class Error(val errorMsg: String) : CharacterDetailsUiState
}