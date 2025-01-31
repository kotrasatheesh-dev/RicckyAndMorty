package com.example.presentation.navigation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.repository.Character


@Composable
fun     CharactersList(
    charactersList: List<Character>,
    innerPadding: PaddingValues,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier


) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = innerPadding,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(charactersList.size) { index ->
            val item = charactersList[index]
            CharactersListItem(
                item.image ?: "",
                item.name ?: "",
                onClick = { onNavigate("character_details/${item.id}") }
            )
        }
    }
}



