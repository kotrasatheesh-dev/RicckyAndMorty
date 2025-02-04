package domain.repository

import domain.mapper.CharacterDetailsMapper

interface CharacterDetailsRepository {
    suspend fun getCharacterDetailsById(id : String) : Result<CharacterDetailsMapper>
}
