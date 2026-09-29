package com.bd.multiverseexplorer.domain.repository

import androidx.paging.PagingData
import com.bd.multiverseexplorer.domain.model.AppResult
import com.bd.multiverseexplorer.domain.model.Character
import com.bd.multiverseexplorer.domain.model.CharacterStatus
import kotlinx.coroutines.flow.Flow

interface  CharacterRepository{
    suspend fun getCharacters(
        searchQuery: String,
        status: CharacterStatus
    ): Flow<PagingData<Character>>

    suspend fun getCharacter(id: Int?) : AppResult<Character>
}