package common.module.helpers

sealed class NavigationRoutes(val route: String) {
    object AllCharacters : NavigationRoutes("allCharacters")
    object CharacterDetails : NavigationRoutes("details/{characterId}/{topBarTitle}") {
        fun createRoute(characterId: String, topBarTitle: String): String =
            "details/$characterId/$topBarTitle"
    }
}
