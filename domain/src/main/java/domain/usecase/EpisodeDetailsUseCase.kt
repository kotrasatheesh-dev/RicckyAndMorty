package domain.usecase

import domain.mapper.EpisodeDetails
import domain.mapper.EpisodeDetailsMapper
import domain.repository.EpisodeDetailsRepository
import javax.inject.Inject

class EpisodeDetailsUseCase @Inject constructor(private val episodeDetailsRepository: EpisodeDetailsRepository){
    suspend fun invoke(episodeId : String): Result<EpisodeDetailsMapper> {
        return episodeDetailsRepository.getEpisodeDetailsById(episodeId)
    }
}
