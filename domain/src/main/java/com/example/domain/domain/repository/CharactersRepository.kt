package com.example.domain.domain.repository

import androidx.paging.PagingData
import com.example.common.GetCharactersQuery
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacters(): Flow<PagingData<GetCharactersQuery.Result>>

}
