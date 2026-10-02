package com.bd.shared.domain.usecase

import com.bd.shared.domain.model.AppResult
import com.bd.shared.domain.model.Character
import com.bd.shared.domain.repository.CharacterRepository

class GetCharacterUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(id: Int) : AppResult<Character> {
        return characterRepository.getCharacter(id)
    }
}