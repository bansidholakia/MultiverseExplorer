package com.bd.shared.data.local.database

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import com.bd.shared.data.local.dao.FavoriteCharacterDao
import com.bd.shared.data.local.entity.FavoriteCharacterEntity

@Database(
    entities = [
        FavoriteCharacterEntity::class
    ],
    version = 1,
    exportSchema = true
)
@ConstructedBy(
    MultiverseDatabaseConstructor::class
)
abstract class MultiverseDatabase : RoomDatabase(){

    abstract fun favoriteCharacterDao() : FavoriteCharacterDao
}

@Suppress("KotlinNoActualForExpect")
expect object MultiverseDatabaseConstructor :
    RoomDatabaseConstructor<MultiverseDatabase> {

    override fun initialize():
            MultiverseDatabase
}