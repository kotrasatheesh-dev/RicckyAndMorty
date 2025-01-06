package com.rickyandmonty.di

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.rickyandmonty.di.navigation.NavigationController
import com.rickyandmonty.R

@Composable
fun HomesScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier.fillMaxSize(),  // Use the passed modifier here
        topBar = { RickAndMortyAppBar(stringResource(R.string.app_name)) },
        content = { innerPadding ->
            NavigationController(navController, Modifier.padding(innerPadding))
        },
    )
}

