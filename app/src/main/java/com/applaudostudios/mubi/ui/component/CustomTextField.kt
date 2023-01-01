package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.applaudostudios.mubi.ui.theme.MubiPalette


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    paddingIconEnd: Dp = 4.dp,
    paddingIconStart: Int = 16,
    placeholder: String = "",
    leadingIcon: (@Composable() () -> Unit)? = null,
    overInputPlaceholder: Boolean = true,
    isPassword: Boolean = false,
    value: String = "",
    onValueChange: (String) -> Unit = {}
) {

    Column() {
        if (overInputPlaceholder)
            Text(text = placeholder, modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
        Row(
            modifier = modifier
                .background(MubiPalette.Gray, shape = RoundedCornerShape(24.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalSpace(paddingIconStart)
            leadingIcon?.let {
                it()
            }
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = paddingIconEnd),
                value = value,
                onValueChange = onValueChange,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MubiPalette.DarkColorPalette.background,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    placeholderColor = MubiPalette.SubtleText
                ),
                placeholder = {
                    Text(text = placeholder)
                },
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
            )

        }
    }
}

