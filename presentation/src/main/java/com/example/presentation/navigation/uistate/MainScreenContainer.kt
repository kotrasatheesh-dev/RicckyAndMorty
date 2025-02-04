package com.example.presentation.navigation.uistate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.presentation.navigation.MainScreen
import com.example.presentation.navigation.viewmodel.CharactersViewModel

@Composable

fun MainScreenContainer(viewModel: CharactersViewModel) {
    val uiState = viewModel.charactersState.collectAsState().value

    MainScreen(
        uiState = uiState,
        onLoadCharacters = { viewModel.fetchData() }
    )
}
