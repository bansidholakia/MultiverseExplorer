package com.bd.multiverseexplorer.presentation.character.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bd.shared.domain.model.CharacterStatus

@Composable
fun StatusFilter(
    selectedStatus : CharacterStatus,
    onStatusSelected: (CharacterStatus) -> Unit,
    modifier: Modifier = Modifier
){

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(CharacterStatus.entries.size) {
            index ->
            FilterChip(
                selected = selectedStatus == CharacterStatus.entries[index],
                onClick = {
                    onStatusSelected(CharacterStatus.entries[index])
                },
                label = { Text("${CharacterStatus.entries[index]}") }
            )
        }
    }
}