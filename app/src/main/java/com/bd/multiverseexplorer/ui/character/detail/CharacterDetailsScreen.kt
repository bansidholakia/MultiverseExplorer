package com.bd.multiverseexplorer.ui.character.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bd.multiverseexplorer.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    uiState: CharacterDetailsUiState,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Character Detail")},
                navigationIcon = { TextButton (onBackClick){ Text("Back") } }
            )
        },
    ) { innerPadding ->

        when(uiState){
            is CharacterDetailsUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ){
                    Text(uiState.errorMsg)
                }
            }
            CharacterDetailsUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment =
                        Alignment.Center
                ) {

                    CircularProgressIndicator()
                }
            }
            is CharacterDetailsUiState.Success -> {
                CharacterDetailsContent(
                    character =
                        uiState.character,
                    isFavorite = uiState.isFavorite,
                    onFavoriteClick =
                        onFavoriteClick,
                    modifier =
                        Modifier.padding(
                            innerPadding
                        )
                )
            }
        }
    }
}

@Composable
private fun CharacterDetailsContent(
    character: Character,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp).verticalScroll(
            rememberScrollState()
        ).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        AsyncImage(
            model = character.imageUrl,
            contentDescription = character.name,
            modifier = Modifier.size(200.dp).clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )
        Text(character.name, style = MaterialTheme.typography.headlineMedium)

        FilledTonalButton(
            onClick =
                onFavoriteClick
        ) {

            Text(
                if (isFavorite) {
                    "Remove from Favorites"
                } else {
                    "Add to Favorites"
                }
            )
        }

        HorizontalDivider()

        CharacterInfoRow(
            label = "Status",
            value = character.status
        )

        CharacterInfoRow(
            label = "Species",
            value = character.species
        )

        if (character.type.isNotBlank()) {

            CharacterInfoRow(
                label = "Type",
                value = character.type
            )
        }

        CharacterInfoRow(
            label = "Gender",
            value = character.gender
        )

        CharacterInfoRow(
            label = "Origin",
            value = character.originName
        )

        CharacterInfoRow(
            label = "Location",
            value = character.locationName
        )

        CharacterInfoRow(
            label = "Episodes",
            value = character.episodeUrls.size.toString()
        )
    }
}

@Composable
fun CharacterInfoRow(
    label: String,
    value: String
){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        Text(label, style = MaterialTheme.typography.bodyLarge)
        Text(value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
    }

}
