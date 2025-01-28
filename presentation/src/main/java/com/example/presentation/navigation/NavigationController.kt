package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.presentation.navigation.characters.AllCharacters
import com.example.presentation.navigation.characterdetails.CharacterDetails
import com.example.presentation.navigation.uistate.UiState
import common.module.helpers.NavigationRoutes
import androidx.navigation.compose.composable

@Composable
fun NavigationController(
    navController: NavHostController,
    charactersState: UiState,
    onLoadCharacters: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.AllCharacters.name,
        modifier = modifier,
    ) {
        composable(route = NavigationRoutes.AllCharacters.name) {
            AllCharacters(
                uiState = charactersState,
                onLoadCharacters = onLoadCharacters,
                onNavigateToCharacterDetails = { characterId, topBarTitle ->
                    navController.navigate("details/$characterId/$topBarTitle")
                }
            )
        }
        composable(
            route = "details/{characterId}/{topBarTitle}",
            arguments = listOf(
                navArgument("characterId") { type = NavType.StringType },
                navArgument("topBarTitle") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId") ?: return@composable
            val topBarTitle = backStackEntry.arguments?.getString("topBarTitle") ?: "Character Details"
            CharacterDetails(characterId = characterId, topBarTitle = topBarTitle)
        }
    }
}
