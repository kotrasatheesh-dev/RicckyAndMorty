package domain.maper


import com.exmple.rickandmorty.GetCharactersQuery
import domain.model.Character

object CharacterMapper {
    fun map(result: zGetCharactersQuery.Result): Character {
        return Character(
            id = result.id ?: "",
            name = result.name ?: "Unknown",
            image = result.image ?: ""
        )
    }

    fun mapList(results: List<GetCharactersQuery.Result?>?): List<Character> {
        return results
            ?.filterNotNull()
            ?.map { map(it) }
            ?: emptyList()
    }
}

