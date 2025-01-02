package com.example.data.repository

import CharacterPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.example.common.GetCharactersQuery
import com.example.domain.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl
@Inject
constructor(
    val apolloClient: ApolloClient,
) : CharactersRepository {
    override suspend fun getCharacters(): Flow<PagingData<GetCharactersQuery.Result>> =
        Pager(
            config = PagingConfig(pageSize = 20), // Set your desired page size
            pagingSourceFactory = {
                CharacterPagingSource(
                    apolloClient
                )
            },
        ).flow
}