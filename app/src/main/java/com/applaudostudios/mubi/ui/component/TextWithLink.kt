package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.applaudostudios.mubi.ui.theme.MubiPalette

@Composable
fun TextWithLink(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: Pair<String, () -> Unit>,
    linkTextColor: Color = MubiPalette.Link,
    linkTextFontWeight: FontWeight = FontWeight.Bold,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        val startIndex = fullText.indexOf(linkText.first)
        val endIndex = startIndex + linkText.first.length
        addStyle(
            style = SpanStyle(
                color = linkTextColor,
                fontSize = fontSize,
                fontWeight = linkTextFontWeight,
                textDecoration = linkTextDecoration
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = "",
            start = startIndex,
            end = endIndex
        )

    }

    Column(modifier = Modifier.padding(16.dp)) {
        ClickableText(
            modifier = modifier,
            text = annotatedString,
            style = TextStyle(
                color = MubiPalette.Text,
                textAlign = TextAlign.Center,
                fontWeight = linkTextFontWeight,
                fontSize = fontSize
            ),
            onClick = {
                annotatedString.getStringAnnotations("URL", it, it)
                    .firstOrNull()?.let { _ ->
                        linkText.second()
                    }
            }
        )
    }
}