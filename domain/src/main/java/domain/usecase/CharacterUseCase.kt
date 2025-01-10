package domain.usecase

import data.model.Character
import domain.repository.CharactersRepository
import javax.inject.Inject

class CharacterUseCase
@Inject
constructor(
    private val charactersRepository: CharactersRepository,
) {
    suspend fun invokeCharacters(): List<data.model.Character> =
        charactersRepository.getCharacters()
}
