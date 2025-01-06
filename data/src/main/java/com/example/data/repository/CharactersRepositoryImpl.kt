package com.example.data.repository

import com.apollographql.apollo.ApolloClient
import com.example.common.GetCharactersQuery
import com.example.domain.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl
@Inject
constructor(
    private val apolloClient: ApolloClient,
) : CharactersRepository {
    override suspend fun getCharacters(): Flow<List<GetCharactersQuery.Result>> = flow {
        val response = apolloClient.query(GetCharactersQuery()).execute()
        val characters = response.data?.characters?.results ?: emptyList()
        emit(characters.filterNotNull()) // Emit the list of characters
    }
}

