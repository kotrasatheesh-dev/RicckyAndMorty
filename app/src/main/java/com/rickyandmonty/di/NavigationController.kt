package com.rickyandmonty.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rickyandmonty.di.utils.NavigationRoutes

@Composable
fun NavigationController(
    navController: NavHostController,
    modifier: Modifier = Modifier,  // Add default value for modifier
) {
    NavHost(
        navController,
        NavigationRoutes.AllCharacters.name,
        modifier = modifier,
    ) {
        // Write code here
    }
}
