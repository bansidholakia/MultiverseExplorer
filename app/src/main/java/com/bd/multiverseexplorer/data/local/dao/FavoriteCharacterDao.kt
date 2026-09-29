package com.bd.multiverseexplorer.data.local.dao

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Upsert
import com.bd.multiverseexplorer.data.local.entity.FavoriteCharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharacterDao{

    @Query(
        """
        SELECT *
        FROM favorite_characters
        ORDER BY name ASC
        """
    )
    fun observeFavorites():
            Flow<List<FavoriteCharacterEntity>>

    @Query(
        """
        SELECT EXISTS(
            SELECT 1
            FROM favorite_characters
            WHERE id = :characterId
        )
        """
    )
    fun observeIsFavorite(
        characterId: Int
    ): Flow<Boolean>

    @Upsert
    suspend fun upsertFavorite(
        character: FavoriteCharacterEntity
    )

    @Query(
        """
        DELETE FROM favorite_characters
        WHERE id = :characterId
        """
    )
    suspend fun deleteFavorite(
        characterId: Int
    )
}