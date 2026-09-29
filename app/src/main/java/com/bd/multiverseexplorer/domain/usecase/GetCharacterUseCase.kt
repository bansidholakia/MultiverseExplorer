package com.bd.multiverseexplorer.domain.usecase

import com.bd.multiverseexplorer.domain.model.AppResult
import com.bd.multiverseexplorer.domain.model.Character
import com.bd.multiverseexplorer.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(id: Int) : AppResult<Character> {
        return characterRepository.getCharacter(id)
    }
}