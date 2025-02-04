package data.repository

import com.apollographql.apollo.ApolloClient
import com.exmple.rickandmorty.GetEpisodeDetailsByIdQuery
import domain.mapper.EpisodeDetailsMapper
import domain.repository.EpisodeDetailsRepository
import javax.inject.Inject

class EpisodeDetailsRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) : EpisodeDetailsRepository {
    override suspend fun getEpisodeDetailsById(id: String): Result<EpisodeDetailsMapper> {
        return try {
            val response = apolloClient.query(GetEpisodeDetailsByIdQuery(id)).execute()
            if (response.hasErrors()) {
                Result.failure(Exception(response.errors?.joinToString { it.message }))
            } else {
                val episodeDetails = response.data?.episode
                var characters = episodeDetails?.characters?.map { character ->
                    EpisodeDetailsMapper.Character(
                        id = character?.id.orEmpty(),
                        name = character?.name.orEmpty(),
                        image = character?.image.orEmpty()
                    )
                }
                episodeDetails?.let {
                    Result.success(
                        EpisodeDetailsMapper(
                            id = it.id,
                            name = it.name,
                            airDate = it.air_date,
                            characters = characters
                        )
                    )
                } ?: Result.failure(Exception("Episode details not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
