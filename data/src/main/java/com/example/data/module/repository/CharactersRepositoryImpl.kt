package com.example.data.module.repository

import com.apollographql.apollo.ApolloClient
import com.example.data.GetCharactersQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl
@Inject
constructor(
    val apolloClient: ApolloClient,
) : CharactersRepository {

    override suspend fun getCharacters(): Flow<List<GetCharactersQuery.Result>> =
        flow {
            val response = apolloClient.query(GetCharactersQuery()).execute()
            val characters = response.data?.characters?.results?.filterNotNull().orEmpty()
            emit(characters)
        }
}
