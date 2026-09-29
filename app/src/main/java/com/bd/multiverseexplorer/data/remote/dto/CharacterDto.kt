package com.bd.multiverseexplorer.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,

    val name: String,

    val status: String,

    val species: String,

    val type: String,

    val gender: String,

    val origin: LocationReferenceDto,

    val location: LocationReferenceDto,

    val image: String,

    val episode: List<String>,

    val url: String,

    val created: String
)