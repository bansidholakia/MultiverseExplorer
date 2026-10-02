package com.bd.multiverseexplorer.data

import androidx.paging.PagingData
import com.bd.shared.domain.model.Character
import kotlinx.coroutines.flow.MutableStateFlow


val fakeCharacterData = listOf(
    Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        originName = "Earth (C-137)",
        locationName = "Citadel of Ricks",
        imageUrl =
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episodeUrls = emptyList()
    ),
    Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        originName = "Earth (C-137)",
        locationName = "Citadel of Ricks",
        imageUrl =
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episodeUrls = emptyList()
    ),
)

val fakeCharacterDataFlow = MutableStateFlow(PagingData.from(fakeCharacterData))