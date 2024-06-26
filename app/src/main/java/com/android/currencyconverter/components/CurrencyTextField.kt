package com.android.currencyconverter.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.android.currencyconverter.R
import java.math.BigDecimal
import java.math.BigInteger
import java.text.NumberFormat
import java.util.Locale

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 19/12/2023.
 * se.muhammadimran@gmail.com
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CurrencyEditText(onValueChanged: (BigDecimal) -> Unit = {}) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val textFieldContentDescription = stringResource(id = R.string.currency_text_field)
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = textFieldContentDescription },
        value = text,
        onValueChange = {
            val newString = it.filter { char ->
                char == ".".first()
            }
            if (newString.length <= 1 && !it.contains(",")) {
                text = it
                Log.d(">>>>>>","Number is: $it")
                onValueChanged(it.toBigDecimalOrNull()?:(0.0).toBigDecimal())
            }
        },
        singleLine = true,
        placeholder = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("# ### ###.##")
            }
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done,
        ),
        visualTransformation = NumberCommaTransformation(),
        keyboardActions = KeyboardActions(
            onDone = {
                Log.d(">>>>>Currency", "On done is: $text")
                keyboardController?.hide()
            }
        )
    )
}

val String.isValidToFormatAmount get(): Boolean = isNotBlank() && length >= 2


class NumberCommaTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        Log.d(">>>>>Currency", "Input is: ${text.text}")
        val input = text.text.replace(" ", "")
        val formatted = if (input.isValidToFormatAmount) {
            val split = text.text.split(".")
            val wholeNumber = split[0]
            var toAppend = ""
            if (split.size == 2) {
                toAppend = "." + split[1]
            }
            val wholeNumb = wholeNumber.toBigIntegerOrNull()?:0.toBigInteger()
            wholeNumb.formatWithComma().replace(",", " ") + toAppend
        } else
            text.text
        return TransformedText(
            text = AnnotatedString(formatted),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return formatted.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return text.length
                }
            }
        )
    }
}

fun BigInteger?.formatWithComma(): String =
    NumberFormat.getNumberInstance(Locale.US).format(this ?: 0)
