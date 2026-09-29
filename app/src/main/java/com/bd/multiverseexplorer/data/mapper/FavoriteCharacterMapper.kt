package com.bd.multiverseexplorer.data.mapper

import com.bd.multiverseexplorer.data.local.entity.FavoriteCharacterEntity
import com.bd.multiverseexplorer.domain.model.Character

fun Character.toFavoriteEntity():
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

fun FavoriteCharacterEntity.toCharacter():
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