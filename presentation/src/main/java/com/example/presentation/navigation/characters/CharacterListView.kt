    package com.example.presentation.navigation.characters

    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.PaddingValues
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.lazy.grid.GridCells
    import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Card
    import androidx.compose.material3.CardDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.unit.dp
    import domain.repository.Character


    @Composable
    fun CharactersList(
        charactersList: List<Character>,
        innerPadding: PaddingValues,
        onNavigate: (String) -> Unit,
        modifier: Modifier = Modifier


    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(charactersList.size) { index ->
                val item = charactersList[index]
                Card (
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ){
                    CharactersListItem(
                        item.image ?: "",
                        item.name ?: "",
                        item.id ?: "",
                        onClick = { onNavigate("character_details/${item.id}") }
                    )
                }

            }
        }
    }



