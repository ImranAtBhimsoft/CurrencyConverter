package com.android.currencyconverter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.currencyconverter.R
import com.android.currencyconverter.data.CurrencyConverted

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 21/12/2023.
 * se.muhammadimran@gmail.com
 */

/**
 * @see CurrencyGridItem is Grid item which takes
 * @param currency is a
 * @see CurrencyConverted
 */
@Composable
fun CurrencyGridItem(currency: CurrencyConverted) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .testTag(stringResource(id = R.string.currencies_grid_item))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            TextBody(
                text = currency.code,
            )
            TextLabel(
                text = "${currency.rate}",
            )
        }
    }
}
