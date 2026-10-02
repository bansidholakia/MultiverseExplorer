package com.bd.multiverseexplorer.data.mapper

import com.bd.shared.domain.model.Character
import com.bd.multiverseexplorer.data.remote.dto.CharacterDto

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