package com.bd.multiverseexplorer.presentation.character.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bd.multiverseexplorer.data.fakeCharacterDataFlow
import com.bd.multiverseexplorer.domain.model.Character
import com.bd.multiverseexplorer.domain.model.CharacterStatus
import com.bd.multiverseexplorer.presentation.character.component.CharacterList
import com.bd.multiverseexplorer.presentation.character.component.StatusFilter
import com.bd.multiverseexplorer.presentation.character.component.SearchBar
import com.bd.multiverseexplorer.presentation.theme.MultiverseExplorerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    uiState: CharacterListUiState,
    characters: LazyPagingItems<Character>,
    onCharacterClick : (Int) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onStatusSelected: (CharacterStatus) -> Unit,
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Multiverse Explorer"
                    )
                }
            )
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            SearchBar(
                query = uiState.searchQuery,
                onQueryChange = onSearchQueryChange
            )

            StatusFilter(
                selectedStatus = uiState.selectedStatus,
                onStatusSelected = onStatusSelected
            )

            val refreshState = characters.loadState.refresh

            when {
                refreshState is LoadState.Loading &&
                        characters.itemCount == 0 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment =
                            Alignment.Center
                    ) {

                        CircularProgressIndicator()
                    }
                }

                refreshState is LoadState.Error -> {
                    ErrorContent(
                        message =
                            refreshState.error.message ?: "Something went wrong",
                        onRetry = {
                            characters.retry()
                        },
                        modifier =
                            Modifier.weight(1f)
                    )
                }

                refreshState is LoadState.NotLoading && characters.itemCount == 0 -> {
                    EmptyListView()
                }

                else -> {
                    CharacterList(
                        characters = characters,
                        onCharacterClick = onCharacterClick
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview() {
    MultiverseExplorerTheme {
        CharacterListScreen(
            uiState = CharacterListUiState("", CharacterStatus.ALL),
            characters = fakeCharacterDataFlow.collectAsLazyPagingItems(),
            onCharacterClick = {},
            onSearchQueryChange = {},
            onStatusSelected = { TODO() }
        )
    }
}

@Composable
fun EmptyListView(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No characters found")
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = message
        )

        Spacer(
            modifier = modifier.height(12.dp)
        )

        Button(
            onClick = onRetry
        ) {

            Text("Retry")
        }
    }
}