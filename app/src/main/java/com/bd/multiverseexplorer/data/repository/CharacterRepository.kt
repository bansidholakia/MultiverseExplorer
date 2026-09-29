package com.bd.multiverseexplorer.data.repository

import androidx.paging.PagingData
import com.bd.multiverseexplorer.model.Character
import com.bd.multiverseexplorer.model.CharacterStatus
import kotlinx.coroutines.flow.Flow

interface  CharacterRepository{
    suspend fun getCharacters(
        searchQuery: String,
        status: CharacterStatus
    ): Flow<PagingData<Character>>

    suspend fun getCharacter(id: Int?) : Character?
}