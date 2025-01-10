package domain.usecase

import data.module.Character
import domain.repository.CharactersRepository
import javax.inject.Inject

class CharacterUseCase
@Inject
constructor(
    private val charactersRepository: CharactersRepository,
) {
    suspend fun invokeCharacters(): List<data.module.Character> =
        charactersRepository.getCharacters()
}
