package domain.mapper

import domain.repository.Character

fun EpisodeDetailsMapper.toEpisodeDetails(): EpisodeDetails {
    return EpisodeDetails(
        id = this.id ?: "",
        airDate = this.airDate ?: "",
        title = this.name ?: "",
        characters = this.characters?.map { it.toCharacter() } ?: emptyList() // ✅ Convert characters
    )
}
fun EpisodeDetailsMapper.Character.toCharacter(): Character {
    return Character(
        id = this.id,
        name = this.name,
        image = this.image
    )
}
