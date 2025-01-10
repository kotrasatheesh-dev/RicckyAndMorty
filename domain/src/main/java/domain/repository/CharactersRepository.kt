    package domain.repository

    import domain.model.Character

    interface CharactersRepository {
        suspend fun getCharacters(): List<Character>
    }
