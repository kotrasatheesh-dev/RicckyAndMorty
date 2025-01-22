package domain.usecase


import domain.repository.Character
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
