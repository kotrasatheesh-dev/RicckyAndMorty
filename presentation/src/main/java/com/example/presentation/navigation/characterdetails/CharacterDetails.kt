package com.example.presentation.navigation.characterdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.presentation.R
import com.example.presentation.navigation.uistate.UiState
import domain.mapper.CharacterDetailsMapper
import domain.repository.Character
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CharacterDetails(
    characterDetails: StateFlow<UiState<CharacterDetailsMapper>>,
    navigateToEpisodeDetails: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState = characterDetails.collectAsState().value
    when (uiState) {
        is UiState.Error -> {
            Text(text = uiState.exception.message.toString())
        }
        UiState.Loading -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(dimensionResource(R.dimen.progress_bar_size)))
            }
        }

        is UiState.Success -> {
            CharacterDetailsScreen(uiState.data ,navigateToEpisodeDetails,modifier)
        }
    }
}
@Composable
fun CharacterDetailsScreen(
    characterDetailsMapper: CharacterDetailsMapper,
    navigateToEpisodeDetails: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            CharacterDetailItems(characterDetailsMapper,innerPadding,navigateToEpisodeDetails)
        },
    )
}

@Composable
fun CharacterDetailItems(
    item: CharacterDetailsMapper,
    paddingValues: PaddingValues,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(item.image)

    Column(
        modifier = modifier.padding(paddingValues)
    ) {
        Image(
            painter = painter,
            contentDescription = "Grid Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.character_details_image_height)),
        )
        Column(
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.character_list_grid_spacing))
        ) {
            Text(text = item.name.orEmpty(), style = MaterialTheme.typography.headlineLarge)
            CharacterStatus(item,onEpisodeClick)
        }
        EpisodesList(item, onEpisodeClick)
    }
}

@Composable
fun CharacterStatus(
    item: CharacterDetailsMapper,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = dimensionResource(R.dimen.character_details_status_padding))
    ) {
        Row {
            Text(
                text = stringResource(R.string.status),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.character_details_spacer)))
            CustomTextWithStyleMediumColorPrimary(text = item.status?.uppercase().orEmpty())
        }
        CharacterSpecies(item,onEpisodeClick)
    }
}


@Composable
fun CharacterSpecies(
    item: CharacterDetailsMapper,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = dimensionResource(R.dimen.character_details_spacer))
    ) {
        Row {
            Text(
                text = stringResource(R.string.species),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.character_details_spacer)))
            CustomTextWithStyleMediumColorPrimary(text = item.species?.uppercase().orEmpty())
        }
        CharacterGender(item,onEpisodeClick)
    }
}

@Composable
fun CharacterGender(
    item: CharacterDetailsMapper,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = dimensionResource(R.dimen.character_details_spacer))
    ) {
        Row {
            Text(
                text = stringResource(R.string.gender),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.character_details_spacer)))
            CustomTextWithStyleMediumColorPrimary(text = item.gender?.uppercase().orEmpty())
        }
        CharacterOrigin(item,onEpisodeClick)
    }
}


@Composable
fun CharacterOrigin(
    item: CharacterDetailsMapper,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = dimensionResource(R.dimen.character_details_origin_spacer))
    ) {
        Text(
            text = stringResource(R.string.origin),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.padding(
            top = dimensionResource(R.dimen.character_details_spacer)
        ))
        Row {
            Text(
                text = stringResource(R.string.origin_name),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.character_details_spacer)))
            CustomTextWithStyleMediumColorPrimary(text = item.originName?.uppercase().orEmpty())
        }
        CharacterOriginDimension(item,onEpisodeClick)
    }
}


@Composable
fun CharacterOriginDimension(
    item: CharacterDetailsMapper,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row {
            Text(
                text = stringResource(R.string.origin_dimension),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.character_details_spacer)))
            CustomTextWithStyleMediumColorPrimary(text = item.originDimension?.uppercase().orEmpty())
        }
        CharacterLocation(item,onEpisodeClick)
    }
}

@Composable
fun CharacterLocation(
    item: CharacterDetailsMapper,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.character_details_origin_spacer)))
        Text(text = stringResource(R.string.location), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Row {
            Text(
                text = stringResource(R.string.location_name),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.character_details_spacer)))
            CustomTextWithStyleMediumColorPrimary(text = item.locationName?.uppercase().orEmpty())
        }
        CharacterLocationDimension(item,onEpisodeClick)
    }
}

@Composable
fun CharacterLocationDimension(
    item: CharacterDetailsMapper,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row {
            Text(
                text = stringResource(R.string.location_dimension),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.character_details_spacer)))
            CustomTextWithStyleMediumColorPrimary(text = item.locationDimension?.uppercase().orEmpty())
        }
        EpisodesList(item,onEpisodeClick)
    }
}

@Composable
fun EpisodesList(
    item: CharacterDetailsMapper,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(dimensionResource(R.dimen.character_list_item_padding)),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.character_list_grid_spacing)
        )
    ) {
        items(item.episodes?.size ?: 0) { index ->
            val episode = item.episodes?.get(index)
            EpisodeListItem(episode, index,onEpisodeClick)
        }
    }
}

@Composable
fun EpisodeListItem(
    item: Character.Episode?,
    index: Int,
    onEpisodeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(dimensionResource(R.dimen.episode_item_size))
            .background(color = colorResource(R.color.episode_background_color))
            .clickable { item?.id?.let { onEpisodeClick(it) } }
    ) {
        Column(
            modifier = Modifier.matchParentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val episodeNumber = remember { mutableStateOf("EP $index") }
            Text(
                text = episodeNumber.value,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = item?.episode.orEmpty(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun CustomTextWithStyleMediumColorPrimary(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        text = text.uppercase().orEmpty(),
        modifier = modifier,
        style = style,
        color = color
    )
}








