package com.bd.multiverseexplorer.presentation.character.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun CharacterListRoute(
    viewModel: CharacterListViewModel,
    onCharacterClick: (Int) -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val characters = viewModel.characters.collectAsLazyPagingItems()

    CharacterListScreen(
        uiState = uiState,
        characters = characters,
        onCharacterClick = onCharacterClick,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onStatusSelected = viewModel::onStatusSelected,
    )
}