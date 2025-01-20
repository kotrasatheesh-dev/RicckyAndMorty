package com.example.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.example.presentation.R
import com.example.presentation.navigation.viewmodel.CharactersViewModel
import com.exmple.rickandmorty.GetCharactersQuery
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MainScreen(
    allCharactersState: StateFlow<UiState<List<GetCharactersQuery.Result>>>,
    topBarTitle: String,
    onNavigateToCharacterDetails: (String) -> Unit,
    onLoadCharacters: () -> Unit
) {
    val uiState = allCharactersState.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { RickAndMortyAppBar(topBarTitle) },
        content = { innerPadding ->
            AllCharacters(
                uiState = uiState,
                onLoadCharacters = onLoadCharacters,
                modifier = Modifier.padding(innerPadding),
                onNavigateToCharacterDetails = onNavigateToCharacterDetails
            )
        },
    )
}

