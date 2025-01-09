package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.navigation.characters.AllCharacters
import com.example.presentation.navigation.viewmodel.CharactersViewModel
import common.module.helpers.NavigationRoutes

@Composable
fun NavigationController(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel,
) {
    NavHost(
        navController,
        NavigationRoutes.AllCharacters.name,
        modifier = modifier,
    ) {
        composable(NavigationRoutes.AllCharacters.name) {
            AllCharacters(viewModel)
        }
    }
}


