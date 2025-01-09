package com.example.presentation.navigation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.exmple.rickandmorty.GetCharactersQuery

@Composable
fun CharactersList(
    charactersList: LazyPagingItems<GetCharactersQuery.Result>?,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(charactersList?.itemCount ?: 0) { index ->
            val item = charactersList?.get(index)
            CharactersListItem(item?.character?.image ?: "", item?.character?.name ?: "")
        }
    }
}



