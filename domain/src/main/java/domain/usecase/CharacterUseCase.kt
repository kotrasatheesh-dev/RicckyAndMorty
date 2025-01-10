package domain.usecase

import domain.model.Character
import domain.repository.CharactersRepository
import javax.inject.Inject

class CharacterUseCase
@Inject
constructor(
    private val charactersRepository: CharactersRepository,
) {
    suspend fun invokeCharacters(): List<Character> =
        charactersRepository.getCharacters()
}
