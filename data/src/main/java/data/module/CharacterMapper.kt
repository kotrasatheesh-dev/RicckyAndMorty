    package data.module

    import com.exmple.rickandmorty.GetCharactersQuery
    import domain.repository.Character


    object CharacterMapper {
        fun map(result: GetCharactersQuery.Result): Character {
            return Character(
                id = result.id,
                name = result.name,
                image = result.image
            )
        }

        fun mapList(results: List<GetCharactersQuery.Result?>?): List<Character>? {
            return results
                ?.filterNotNull()
                ?.takeIf { it.isNotEmpty() }
                ?.map { map(it) }
        }
    }
