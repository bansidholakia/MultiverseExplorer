package com.bd.multiverseexplorer.data.local.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(
    tableName = "favorite_characters"
)

data class FavoriteCharacterEntity(

    @PrimaryKey
    val id: Int,

    val name: String,

    val status: String,

    val species: String,

    val type: String,

    val gender: String,

    val originName: String,

    val locationName: String,

    val imageUrl: String
)