package com.bd.multiverseexplorer.presentation.character.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CharacterDetailsRoute(
    viewModel: CharacterDetailsViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterDetailsScreen(
        uiState,
        onBackClick,
        onFavoriteClick =
        viewModel::toggleFavorite)
}