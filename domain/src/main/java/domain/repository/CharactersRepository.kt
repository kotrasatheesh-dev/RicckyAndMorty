package repository

import com.example.data.GetCharactersQuery
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacters(): Flow<List<GetCharactersQuery.Result>>
}
