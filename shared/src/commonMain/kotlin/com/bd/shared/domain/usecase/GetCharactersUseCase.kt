package com.bd.shared.domain.usecase

import androidx.paging.PagingData
import com.bd.shared.domain.model.CharacterStatus
import com.bd.shared.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import com.bd.shared.domain.model.Character

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {

     operator fun invoke(
        searchQuery: String,
        status: CharacterStatus
    ): Flow<PagingData<Character>> {

        return repository.getCharacters(
            searchQuery = searchQuery.trim(),
            status = status
        )
    }
}