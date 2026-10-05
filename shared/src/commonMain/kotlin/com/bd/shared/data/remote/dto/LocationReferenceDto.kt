package com.bd.shared.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationReferenceDto(
    val name: String,
    val url: String
)