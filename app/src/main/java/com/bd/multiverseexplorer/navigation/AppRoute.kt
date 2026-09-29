package com.bd.multiverseexplorer.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object CharacterRoute: NavKey

@Serializable
data class CharacterDetailsRoute(
    val characterId: Int
) : NavKey