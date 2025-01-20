package com.example.presentation.navigation.characters

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.navigation.uistate.UiState

@Composable
fun AllCharacters(
    uiState: UiState<List<GetCharactersQuery.Result>>,
    onLoadCharacters: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToCharacterDetails: (String) -> Unit,
) {
    when (uiState) {
        is UiState.Empty -> {
            onLoadCharacters()
        }
        is UiState.Error -> {
            // Display an error message or retry button
        }
        is UiState.Loading -> {
            CircularProgressIndicator(modifier = modifier.size(100.dp))
        }
        is UiState.Success -> {
            val charactersList = uiState.data
            CharactersList(
                charactersList = charactersList,
                onNavigateToCharacterDetails = onNavigateToCharacterDetails,
                modifier = modifier
            )
        }
        else -> Unit
    }
}

}




