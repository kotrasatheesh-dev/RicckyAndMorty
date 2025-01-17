package domain.repository

data class Character(
    val id: String,
    val name: String,
    val image: String
){
    data class Episode(
        val id: String,
        val name: String,
        val airDate: String
    )
}
