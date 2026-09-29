package com.bd.multiverseexplorer.remote.dto.mapper

import com.bd.multiverseexplorer.model.Character
import com.bd.multiverseexplorer.remote.dto.CharacterDto

fun CharacterDto.toCharacter(): Character{
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        originName = origin.name,
        locationName = location.name,
        imageUrl = image,
        episodeUrls = episode
    )
}