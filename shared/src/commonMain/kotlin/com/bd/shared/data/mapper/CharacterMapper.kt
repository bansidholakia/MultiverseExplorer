package com.bd.shared.data.mapper

import com.bd.shared.data.remote.dto.CharacterDto
import com.bd.shared.domain.model.Character

internal fun CharacterDto.toCharacter(): Character{
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