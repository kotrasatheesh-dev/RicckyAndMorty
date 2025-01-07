package domain.usecase

import androidx.paging.PagingData
import com.exmple.rickandmorty.GetCharactersQuery
import domain.repository.CharactersRepository
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
