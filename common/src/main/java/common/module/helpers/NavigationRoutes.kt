package common.module.helpers

sealed class NavigationRoutes(val route: String) {
    data object AllCharacters : NavigationRoutes("AllCharacters")
    data object CharacterDetails : NavigationRoutes("character_details/{CharacterId}") {
        fun createRoute(characterId: String) = "character_details/$characterId"
    }
    data object EpisodeDetails : NavigationRoutes("episode_details/{EpisodeId}") {
        fun createRoute(episodeId: String) = "episode_details/$episodeId"
    }

}

