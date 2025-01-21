package data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloException
import com.exmple.rickandmorty.GetCharactersQuery
import domain.repository.Character
import data.module.CharacterMapper
import domain.repository.CharactersRepository
import exception.DataAccessException
import javax.inject.Inject

class CharactersRepositoryImpl
@Inject
constructor(
    private val apolloClient: ApolloClient,
) : CharactersRepository {

    override suspend fun getCharacters(): List<Character> {
        try {
            val response = apolloClient.query(GetCharactersQuery()).execute()
            val results = response.data?.characters?.results
            if (results.isNullOrEmpty()||response.hasErrors() ) {
                val errorMessage = response.errors?.firstOrNull()?.message.orEmpty()
                throw DataAccessException.ApiException(
                    errorMessage.toString()
                )
            }
            return CharacterMapper.mapList(results)
        } catch (e: ApolloException) {
            throw DataAccessException.NetworkException(e.message.toString())
        } catch (e: Exception) {
            throw DataAccessException.UnknownException(e.message.toString())
        }
    }
}
