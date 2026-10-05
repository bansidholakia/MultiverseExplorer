package com.bd.multiverseexplorer.fake

import com.bd.shared.data.remote.dto.CharacterDto
import com.bd.shared.data.remote.dto.LocationReferenceDto

val testCharacterDto = CharacterDto(
    id = 1,
    name = "Rick Sanchez",
    status = "Alive",
    species = "Human",
    type = "",
    gender = "Male",

    origin = LocationReferenceDto(
        name = "Earth (C-137)",
        url = "origin-url"
    ),

    location = LocationReferenceDto(
        name = "Citadel of Ricks",
        url = "location-url"
    ),

    image = "rick.jpg",

    episode = listOf(
        "episode-1",
        "episode-2"
    ),

    url = "character-url",

    created = "2017-11-04"
)