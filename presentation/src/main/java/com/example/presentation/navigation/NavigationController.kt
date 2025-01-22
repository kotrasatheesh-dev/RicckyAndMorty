package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.navigation.characters.AllCharacters
import com.example.presentation.navigation.uistate.UiState
import com.example.presentation.navigation.viewmodel.CharactersViewModel
import common.module.helpers.NavigationRoutes

@Composable
fun NavigationController(
    navController: NavHostController,
    charactersState: UiState,
    onLoadCharacters: () -> Unit,
    modifier:Modifier  = Modifier,
) {
    NavHost(
        navController,
        startDestination = NavigationRoutes.AllCharacters.name,
        modifier = modifier,
    ) {
        composable(NavigationRoutes.AllCharacters.name) {
            AllCharacters(
                uiState = charactersState,
                onLoadCharacters = onLoadCharacters
            )
        }
    }
}

