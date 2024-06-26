package com.android.currencyconverter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.android.currencyconverter.R
import com.android.currencyconverter.data.Currency

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 21/12/2023.
 * se.muhammadimran@gmail.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyListItem(currency: Currency, onItemClicked: (Currency) -> Unit) {
    val cDescription = stringResource(id = R.string.currency_list_item)
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .fillMaxWidth()
            .semantics {  contentDescription = cDescription },
        onClick = {
            onItemClicked.invoke(currency)
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            TextHeading(
                text = currency.name,
            )
            TextBody(
                text = currency.code,
            )
        }
    }
}