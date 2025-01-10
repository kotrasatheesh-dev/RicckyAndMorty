    package domain.repository

    interface CharactersRepository {
        suspend fun getCharacters(): List<Character>
    }
