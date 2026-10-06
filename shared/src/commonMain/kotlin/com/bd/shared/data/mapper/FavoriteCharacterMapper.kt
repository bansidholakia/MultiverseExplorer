package com.bd.shared.data.mapper

import com.bd.shared.data.local.entity.FavoriteCharacterEntity
import com.bd.shared.domain.model.Character

internal fun Character
        .toFavoriteEntity():
        FavoriteCharacterEntity {

    return FavoriteCharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        originName = originName,
        locationName = locationName,
        imageUrl = imageUrl
    )
}

internal fun FavoriteCharacterEntity
        .toCharacter():
        Character {

    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        originName = originName,
        locationName = locationName,
        imageUrl = imageUrl,
        episodeUrls = emptyList()
    )
}