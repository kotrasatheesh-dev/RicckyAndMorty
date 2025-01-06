package com.example.domain.domain.repository

import com.example.common.GetCharactersQuery
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacters(): Flow<List<GetCharactersQuery.Result>>

}
