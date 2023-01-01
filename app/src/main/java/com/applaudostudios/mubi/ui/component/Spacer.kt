package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpace(space: Int = 16) {
    Column(modifier = Modifier.height(space.dp)) {}
}

@Composable
fun HorizontalSpace(space: Int = 16) {
    Row(modifier = Modifier.width(space.dp)) {}
}