package data.module

import com.exmple.rickandmorty.GetCharacterDetailsByIdQuery
import domain.mapper.CharacterDetailsMapper
import domain.repository.Character

class CharacterDetailsMapperFactory {
    fun mapCharacterDetails(characterDetails: GetCharacterDetailsByIdQuery.Character?): Result<CharacterDetailsMapper> {
        val episodes = characterDetails?.character?.episode?.map { episode ->
            Character.Episode(
                id = episode?.episode.orEmpty(),
                name = episode?.name.orEmpty(),
                airDate = episode?.air_date.orEmpty()
            )
        }.orEmpty()

        return characterDetails?.let {
            Result.success(
                CharacterDetailsMapper(
                    id = it.character.id.orEmpty(),
                    name = it.character.name.orEmpty(),
                    image = it.character.image.orEmpty(),
                    status = it.character.status.orEmpty(),
                    species = it.character.species.orEmpty(),
                    gender = it.character.gender.orEmpty(),
                    originName = it.character.origin?.name.orEmpty(),
                    originDimension = it.character.origin?.dimension.orEmpty(),
                    locationName = it.character.location?.location?.name.orEmpty(),
                    locationDimension = it.character.location?.location?.dimension.orEmpty(),
                    episodes = episodes
                )
            )
        } ?: Result.failure(Exception("Character details not found"))
    }
}
