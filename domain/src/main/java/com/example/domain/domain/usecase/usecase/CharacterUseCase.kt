package usecases

import androidx.paging.PagingData
import com.example.common.GetCharactersQuery
import com.example.domain.domain.repository.CharactersRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCase
@Inject
constructor(
    private val charactersRepository: CharactersRepository,
) {
    suspend fun invokeCharacters(): Flow<PagingData<GetCharactersQuery.Result>> =
        charactersRepository.getCharacters()
}