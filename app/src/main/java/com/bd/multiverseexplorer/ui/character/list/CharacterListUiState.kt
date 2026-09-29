package com.bd.multiverseexplorer.ui.character.list

import com.bd.multiverseexplorer.model.Character
import com.bd.multiverseexplorer.model.CharacterStatus

data class CharacterListUiState(
    val searchQuery : String = "",
    val selectedStatus : CharacterStatus = CharacterStatus.ALL,
)