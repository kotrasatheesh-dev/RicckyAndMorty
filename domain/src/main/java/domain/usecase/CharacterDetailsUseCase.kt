package domain.usecase

import domain.mapper.CharacterDetailsMapper
import domain.repository.CharacterDetailsRepository
import javax.inject.Inject

class CharacterDetailsUseCase
@Inject
constructor(
    private val characterDetailsRepository : CharacterDetailsRepository,
){
    suspend fun invoke(id : String): Result<CharacterDetailsMapper> {
        return characterDetailsRepository.getCharacterDetailsById(id)
    }
}
