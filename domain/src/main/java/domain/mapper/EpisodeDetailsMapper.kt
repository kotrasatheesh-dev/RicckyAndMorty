package domain.mapper

data class EpisodeDetailsMapper(
    val id: String? = "",
    val name: String? = "",
    val airDate: String? = "",
    val characters: List<Character>?
) {

    data class Character(
        val id: String = "",
        val name: String = "",
        val image: String = ""
    )

}

