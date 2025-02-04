package com.example.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.navigation.characters.AllCharacters
import com.example.presentation.navigation.uistate.UiState
import domain.repository.Character
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MainScreen(
    allCharacters: StateFlow<UiState<List<Character>>>,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            AllCharacters(allCharacters, innerPadding, onNavigate)
        },
    )
}


