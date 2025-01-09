package com.example.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.presentation.R
import com.example.presentation.navigation.viewmodel.CharactersViewModel

@Composable
fun MainScreen(
    viewModel: CharactersViewModel,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val charactersState = viewModel.charactersState.collectAsState().value
    Scaffold(
        modifier = modifier.fillMaxSize(), // Apply modifier here
        topBar = { CharacterAppBar(stringResource(R.string.app_name)) },
        content = { innerPadding ->
            NavigationController(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                charactersState = charactersState,
                onLoadCharacters = { viewModel.fetchData() }
            )
        },
    )
}
