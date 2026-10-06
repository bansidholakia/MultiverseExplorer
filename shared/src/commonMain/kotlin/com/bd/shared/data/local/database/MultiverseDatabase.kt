package com.bd.shared.data.local.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.bd.shared.data.local.dao.FavoriteCharacterDao
import com.bd.shared.data.local.entity.FavoriteCharacterEntity

@Database(
    entities = [
        FavoriteCharacterEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class MultiverseDatabase : RoomDatabase(){

    abstract fun favoriteCharacterDao() : FavoriteCharacterDao
}