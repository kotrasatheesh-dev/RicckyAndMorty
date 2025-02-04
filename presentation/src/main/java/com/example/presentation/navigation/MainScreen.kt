package com.example.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.presentation.R
import com.example.presentation.navigation.uistate.UiState

@Composable
fun MainScreen(
    uiState: UiState,
    onLoadCharacters: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { CharacterAppBar(stringResource(R.string.app_name)) },
        content = { innerPadding ->
            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(innerPadding))
                }
                is UiState.Success -> {
                    NavigationController(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        charactersState = uiState,
                        onLoadCharacters = onLoadCharacters
                    )
                }
                is UiState.Empty -> {
                    Text(
                        text = "No characters available",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                is UiState.Error -> {
                    Text(
                        text = "Error: ${uiState.exception}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        },
    )
}

