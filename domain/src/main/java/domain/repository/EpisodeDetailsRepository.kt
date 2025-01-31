package domain.repository

import domain.mapper.EpisodeDetailsMapper

interface EpisodeDetailsRepository {
    suspend fun getEpisodeDetailsById(id:String): Result<EpisodeDetailsMapper>
}
