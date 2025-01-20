package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.R
import com.example.presentation.navigation.viewmodel.CharacterDetailsViewModel
import com.example.presentation.navigation.viewmodel.CharactersViewModel
import common.module.helpers.NavigationRoutes

@Composable
fun NavigationController(
    viewModelFactory: ViewModelProvider.Factory,
) {
    val topBarTitle = remember { mutableStateOf("") }
    val navController = rememberNavController()
    val onNavigateCharacterDetails: (String) -> Unit = { characterId: String -> navController.navigate(NavigationRoutes.CharacterDetails.createRoute(characterId)) }

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.AllCharacters.route
    ) {
        composable(NavigationRoutes.AllCharacters.route) {
            val viewModel: CharactersViewModel = viewModel(factory = viewModelFactory)
            topBarTitle.value = stringResource(R.string.app_name)
           // MainScreen(allCharacters = viewModel.charactersState, topBarTitle = topBarTitle.value, onNavigateToCharacterDetails = onNavigateCharacterDetails)
        }
        composable(
            route = NavigationRoutes.CharacterDetails.route,
            arguments = listOf(navArgument("CharacterId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val characterId = navBackStackEntry.arguments?.getString("CharacterId")
            val viewModel: CharacterDetailsViewModel = viewModel(factory = viewModelFactory)
            characterId?.let{
               // viewModel.getCharacterDetails(it)
                //topBarTitle.value = viewModel.getCharacterName()
               // CharacterDetails(characterDetails = viewModel.characterDetails, topBarTitle = topBarTitle.value)
            }
        }
    }
}

