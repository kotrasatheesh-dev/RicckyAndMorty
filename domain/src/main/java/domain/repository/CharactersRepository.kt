    package domain.repository

    import data.model.Character

    interface CharactersRepository {
        suspend fun getCharacters(): List<data.model.Character>
    }
