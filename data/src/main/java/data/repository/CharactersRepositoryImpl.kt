package data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloException
import com.exmple.rickandmorty.GetCharactersQuery
import domain.repository.Character
import data.module.CharacterMapper
import domain.repository.CharactersRepository
import exception.RepositoryException
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
            if (response.hasErrors() || results == null) {
                val errorMessage = response.errors?.firstOrNull()?.message.orEmpty()
                throw RepositoryException.ApiException(
                    "Failed to fetch characters: $errorMessage"
                )
            }
            // Mapping response to List<Character>
            return CharacterMapper.mapList(results)
        } catch (e: ApolloException) {
            throw RepositoryException.NetworkException("Network error: ${e.message}")
        } catch (e: Exception) {
            throw RepositoryException.UnknownException("Unknown error: ${e.message}")
        }
    }
}
