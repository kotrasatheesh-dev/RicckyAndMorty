    package domain.repository

    import common.module.model.Character


    interface CharactersRepository {
        suspend fun getCharacters(): List<Character>
    }
