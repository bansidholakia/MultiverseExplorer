package com.bd.shared.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class CharacterResponseDto(
    val info: PageInfoDto,
    val results: List<CharacterDto>
)

@Serializable
data class PageInfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)