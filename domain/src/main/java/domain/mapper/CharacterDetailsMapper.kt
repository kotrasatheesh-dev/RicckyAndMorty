    package domain.mapper

    import domain.repository.Character

    data class CharacterDetailsMapper(
        val id: String,
        val name: String = "",
        val image: String = "",
        val status: String = "",
        val species: String = "",
        val gender: String = "",
        val originName: String = "",
        val originDimension: String = "",
        val locationName: String = "",
        val locationDimension: String = "",
        val episodes: List<Character.Episode?>? = emptyList()
    )

