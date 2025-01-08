package com.example.presentation.navigation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.exmple.rickandmorty.GetCharactersQuery

@Composable
fun CharactersList(charactersList: List<GetCharactersQuery.Result>?) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(charactersList?.size ?: 0) { index ->
            val character = charactersList?.get(index)?.character
            if (character != null) {
                CharactersListItem(
                    imageUrl = character.image ?: "",
                    text = character.name ?: "Unknown"
                )
            }
        }
    }
}

