package com.example.presentation.navigation.characters

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.navigation.uistate.UiState
import com.example.presentation.navigation.viewmodel.CharactersViewModel

@Composable
fun AllCharacters(
    viewModel: CharactersViewModel,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.charactersState.collectAsState().value
    when (uiState) {
        is UiState.Empty -> {}
        is UiState.Error -> {}
        is UiState.Loading -> {
            CircularProgressIndicator(modifier = modifier.size(100.dp))
        }
        is UiState.Success -> {
            val charactersList = uiState.data.collectAsState(initial = emptyList()).value
            CharactersList(charactersList)
        }
        else -> Unit
    }
}


