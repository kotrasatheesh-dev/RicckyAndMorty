    package com.example.domain

    import domain.model.Character
    import domain.repository.CharactersRepository

    class CharactersTestRepository : CharactersRepository {
        override suspend fun getCharacters(): List<Character> {
            return listOf(
                Character(
                    id = "1",
                    name = "Rick Sanchez",
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                ),
                Character(
                    id = "2",
                    name = "Morty Smith",
                    image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
                )
            )
        }
    }
