package com.bd.multiverseexplorer.domain.usecase

import androidx.paging.PagingData
import com.bd.multiverseexplorer.domain.model.CharacterStatus
import com.bd.multiverseexplorer.domain.repository.CharacterRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import com.bd.multiverseexplorer.domain.model.Character

class GetCharactersUseCase @Inject constructor(
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