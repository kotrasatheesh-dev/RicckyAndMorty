package domain.repository

import com.exmple.rickandmorty.GetCharactersQuery
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacters(): Flow<List<GetCharactersQuery.Result>>
}
