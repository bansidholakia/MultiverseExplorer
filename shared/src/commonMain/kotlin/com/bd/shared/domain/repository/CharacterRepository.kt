package com.bd.shared.domain.repository

import androidx.paging.PagingData
import com.bd.shared.domain.model.AppResult
import com.bd.shared.domain.model.Character
import com.bd.shared.domain.model.CharacterStatus
import kotlinx.coroutines.flow.Flow

interface  CharacterRepository{
     fun getCharacters(
        searchQuery: String,
        status: CharacterStatus
    ): Flow<PagingData<Character>>

    suspend fun getCharacter(id: Int?) : AppResult<Character>
}