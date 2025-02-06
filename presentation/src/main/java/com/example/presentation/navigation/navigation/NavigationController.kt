package com.example.presentation.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.navigation.characterdetails.CharacterDetails
import com.example.presentation.navigation.MainScreen
import com.example.presentation.navigation.viewmodel.CharacterDetailsViewModel
import com.example.presentation.navigation.viewmodel.CharactersViewModel
import common.module.helpers.NavigationRoutes
import com.example.presentation.navigation.episodedetails.EpisodeDetails
import com.example.presentation.navigation.viewmodel.EpisodeDetailsViewModel


@Composable
fun NavigationController(
    viewModelFactory: ViewModelProvider.Factory,
) {
    val navController = rememberNavController()
    val navigate: (String) -> Unit = { route: String -> navController.navigate(route) }

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.AllCharacters.route
    ) {
        composable(NavigationRoutes.AllCharacters.route) {
            val viewModel: CharactersViewModel = viewModel(factory = viewModelFactory)
            MainScreen(viewModel.charactersState, navigate)
        }
        composable(
            route = NavigationRoutes.CharacterDetails.route,
            arguments = listOf(navArgument("CharacterId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val characterId = navBackStackEntry.arguments?.getString("CharacterId")
            val viewModel: CharacterDetailsViewModel = viewModel(factory = viewModelFactory)
            characterId?.let { viewModel.getCharacterDetails(it) }

            CharacterDetails(
                characterDetails = viewModel.characterDetails,
                navigateToEpisodeDetails = { episodeId ->
                    navController.navigate(NavigationRoutes.EpisodeDetails.createRoute(episodeId))
                }
            )
        }
        composable(
            route = NavigationRoutes.EpisodeDetails.route,
            arguments = listOf(navArgument("EpisodeId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val episodeId = navBackStackEntry.arguments?.getString("EpisodeId")
            val viewModel: EpisodeDetailsViewModel = viewModel(factory = viewModelFactory)
            episodeId?.let { viewModel.getEpisodeDetailsByUsingEpisodeId(it) }

            EpisodeDetails(
                episodeDetails = viewModel.episodeDetails
            )
        }
    }
}

