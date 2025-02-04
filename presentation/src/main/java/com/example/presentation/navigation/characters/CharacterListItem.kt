package com.example.presentation.navigation.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.exmple.rickandmorty.GetCharactersQuery
import common.module.helpers.NavigationRoutes

@Composable
fun CharactersListItem(
    imageUrl: String,
    characterName: String,
    characterId: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,

) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick(NavigationRoutes.CharacterDetails.createRoute(characterId))
}
    ) {
        val painter = rememberAsyncImagePainter(imageUrl)
        Image(
            painter = painter,
            contentDescription = "Grid Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Text(
            text = characterName,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier =
            Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
        )
    }
}

