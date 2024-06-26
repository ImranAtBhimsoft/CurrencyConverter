package com.android.currencyconverter.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 19/12/2023.
 * se.muhammadimran@gmail.com
 */

/**
 * A utility file containing different styles of
 * composable text
 */

@Composable
fun TextHeading(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = TextUnit.Unspecified

) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = fontSize
    )
}


@Composable
fun TextBody(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier,
        textAlign = textAlign,
    )
}

@Composable
fun TextLabel(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier,
        textAlign = textAlign,
    )
}