package com.bd.multiverseexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.bd.multiverseexplorer.presentation.character.detail.CharacterDetailsRoute
import com.bd.multiverseexplorer.presentation.character.detail.CharacterDetailsViewModel
import com.bd.multiverseexplorer.presentation.character.list.CharacterListRoute
import com.bd.multiverseexplorer.presentation.character.list.CharacterListViewModel

@Composable
fun AppNavigation(){

    val backStack = rememberNavBackStack(
        CharacterRoute
    )

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {

            entry<CharacterRoute> {

                val viewModel = hiltViewModel<CharacterListViewModel>()

                CharacterListRoute(
                    viewModel = viewModel,
                    onCharacterClick = { id ->
                        backStack.add(
                            CharacterDetailsRoute(id)
                        )
                    }
                )
            }

            entry<CharacterDetailsRoute> { route ->

               val viewModel = hiltViewModel<
                       CharacterDetailsViewModel,
                       CharacterDetailsViewModel.Factory>(
                   creationCallback = { factory ->
                       factory.create(route)
                   }
               )

                CharacterDetailsRoute(
                    viewModel = viewModel,
                    onBackClick = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}