package com.bd.multiverseexplorer.ui.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bd.multiverseexplorer.data.repository.CharacterRepository
import com.bd.multiverseexplorer.model.Character
import com.bd.multiverseexplorer.model.CharacterStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@OptIn(
    FlowPreview::class,
    ExperimentalCoroutinesApi::class
)
@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository : CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListUiState())
    val uiState : StateFlow<CharacterListUiState> = _uiState.asStateFlow()

    val characters : Flow<PagingData<Character>> = combine(
        _uiState.map {
            it.searchQuery
        }
            .debounce(400.milliseconds)
            .distinctUntilChanged(),

        _uiState.map{
            it.selectedStatus
        }
            .distinctUntilChanged()
    ){ query, status ->
        query.trim() to status
    }
        .distinctUntilChanged()
        .flatMapLatest {
            (query, status) ->
            repository.getCharacters(searchQuery = query, status = status)
        }
        .cachedIn(viewModelScope)


    fun onSearchQueryChange(query: String){
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    fun onStatusSelected(status: CharacterStatus){
        _uiState.update {
            it.copy(selectedStatus = status)
        }
    }
}