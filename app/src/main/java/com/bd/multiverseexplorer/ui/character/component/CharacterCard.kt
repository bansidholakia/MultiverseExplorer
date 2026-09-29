package com.bd.multiverseexplorer.ui.character.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bd.multiverseexplorer.data.fakeCharacterData
import com.bd.multiverseexplorer.model.Character

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

    ){
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                modifier = Modifier.size(100.dp).clip(
                    RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    character.name,
                    style = MaterialTheme.typography.titleLarge)
                Text(
                    "${character.status} • ${character.species}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    character.gender,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterCardPreview() {
    CharacterCard(
        character = fakeCharacterData[0],
        modifier = Modifier.padding(16.dp),
        onClick = {}
    )
}