package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpace(height: Int = 16) {
    Column(modifier = Modifier.height(height.dp)) {}
}