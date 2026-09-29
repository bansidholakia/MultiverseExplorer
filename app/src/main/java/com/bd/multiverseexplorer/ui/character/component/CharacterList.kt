package com.bd.multiverseexplorer.ui.character.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.bd.multiverseexplorer.model.Character

@Composable
fun CharacterList(
    characters: LazyPagingItems<Character>,
    modifier: Modifier = Modifier,
    onCharacterClick : (Int) -> Unit
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = characters.itemCount,
            key = characters.itemKey {
                character -> character.id
            },
            itemContent = {
                    index ->

                val character = characters[index]

                if (character != null) {
                    CharacterCard(
                        character = character,
                        onClick = { onCharacterClick(character.id) }
                    )
                }
            }
        )

        when(val appendState = characters.loadState.append){
            is LoadState.Error -> {
                item {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text(
                            text =
                                "Couldn't load more characters"
                        )

                        Button(
                            onClick = {
                                characters.retry()
                            }
                        ) {

                            Text("Retry")
                        }
                    }
                }
            }
            LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment =
                            Alignment.Center
                    ) {

                        CircularProgressIndicator()
                    }
                }
            }
            is LoadState.NotLoading -> Unit
        }
    }
}