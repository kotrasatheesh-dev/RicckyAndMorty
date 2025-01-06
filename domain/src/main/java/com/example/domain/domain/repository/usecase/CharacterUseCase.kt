package com.example.domain.domain.repository.usecase

import com.example.common.GetCharactersQuery
import com.example.domain.domain.repository.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCase
@Inject
constructor(
    private val charactersRepository: CharactersRepository,
) {
    suspend fun invokeCharacters(): Flow<List<GetCharactersQuery.Result>> =
        charactersRepository.getCharacters()
}

