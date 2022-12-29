package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.applaudostudios.mubi.ui.theme.Gray

@Composable
fun NavigationListComponent(
    items: List<String>,
    onClick: (index:Int) -> Unit
) {
    val buttonStyle = MubiButtonStyle(height = 40, corners = 20)
    val buttonStyleRegular = buttonStyle.copy(color = Gray)
    val selected = remember { mutableStateOf(0) }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            MubiButton(
                onClick = {
                    selected.value = index
                    onClick(index)
                },
                text = item,
                buttonStyle = if (index == selected.value) buttonStyle else buttonStyleRegular,
                enabled = index != selected.value
            )
        }
    }
}