package com.rickyandmonty.di.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.di.R
import com.rickyandmonty.di.NavigationController

@Composable
fun HomesScreen(modifier: Modifier = Modifier) {  // Add the modifier parameter with a default value
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier.fillMaxSize(),  // Use the passed modifier here
        topBar = { RickAndMortyAppBar(stringResource(R.string.app_name)) },
        content = { innerPadding ->
            NavigationController(navController, Modifier.padding(innerPadding))
        },
    )
}
