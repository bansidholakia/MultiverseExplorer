package com.bd.multiverseexplorer.presentation.character.list

import com.bd.multiverseexplorer.domain.model.CharacterStatus

data class CharacterListUiState(
    val searchQuery : String = "",
    val selectedStatus : CharacterStatus = CharacterStatus.ALL,
)