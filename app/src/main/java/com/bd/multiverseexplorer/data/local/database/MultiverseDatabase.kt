package com.bd.multiverseexplorer.data.local.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.bd.multiverseexplorer.data.local.dao.FavoriteCharacterDao
import com.bd.multiverseexplorer.data.local.entity.FavoriteCharacterEntity

@Database(
    entities = [
        FavoriteCharacterEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MultiverseDatabase : RoomDatabase(){

    abstract fun favoriteCharacterDao() : FavoriteCharacterDao
}