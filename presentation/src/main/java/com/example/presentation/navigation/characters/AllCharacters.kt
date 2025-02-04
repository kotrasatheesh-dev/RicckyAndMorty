package com.example.presentation.navigation.characters

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.presentation.R
import com.example.presentation.navigation.uistate.UiState
import domain.repository.Character
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AllCharacters(
    allCharacters: StateFlow<UiState<List<Character>>>,
    innerPadding: PaddingValues,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = allCharacters.collectAsState().value

    when (uiState) {
        is UiState.Loading -> {
            CircularProgressIndicator(modifier = modifier.size(dimensionResource(R.dimen.progress_bar_size)))
        }
        is UiState.Error -> {
            Text(
                text = uiState.exception.message ?: "An error occurred",
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = modifier
            )
        }
        is UiState.Success -> {
            val charactersList = uiState.data
            CharactersList(charactersList, innerPadding,onNavigate)
        }
    }
}







