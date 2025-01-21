package com.example.presentation.navigation.characters

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.navigation.uistate.UiState
import androidx.compose.material3.CircularProgressIndicator
@Composable
fun AllCharacters(
    uiState: UiState,
    onLoadCharacters: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is UiState.Empty -> {
            onLoadCharacters()
        }
        is UiState.Error -> {
            // Handle error state
        }
        is UiState.Loading -> {
            CircularProgressIndicator(modifier = modifier.size(100.dp))
        }
        is UiState.Success -> {
            val charactersList = uiState.data // Assuming this is a List<GetCharactersQuery.Result>
            CharactersList(
                charactersList = charactersList // Pass the list directly
            )
        }
        else -> Unit
    }
}




