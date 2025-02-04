package data.repository

import com.apollographql.apollo.ApolloClient
import com.exmple.rickandmorty.GetCharacterDetailsByIdQuery
import domain.mapper.CharacterDetailsMapper
import domain.repository.CharacterDetailsRepository
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) :
    private val mapperFactory: CharacterDetailsMapperFactory = CharacterDetailsMapperFactory()
) : CharacterDetailsRepository {
    override suspend fun getCharacterDetailsById(id: String): Result<CharacterDetailsMapper> {
        return try {
            // Execute the GraphQL query using Apollo
            val response = apolloClient.query(GetCharacterDetailsByIdQuery(id)).execute()

            if (response.hasErrors()) {
                Result.failure(Exception(response.errors?.joinToString { it.message }))
            } else {
                val characterDetails = response.data?.character
                // Map the response to your data model
                mapperFactory.mapCharacterDetails(characterDetails)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
