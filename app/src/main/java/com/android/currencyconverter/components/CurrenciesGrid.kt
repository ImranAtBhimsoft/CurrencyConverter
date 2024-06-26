package com.android.currencyconverter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
 * Enhance IT on 25/12/2023.
 * se.muhammadimran@gmail.com
 */

/**
 * @param currenciesConverted is a list of
 * @see CurrencyConverted
 */
@Composable
fun CurrenciesGrid(currenciesConverted: List<CurrencyConverted>){
    LazyVerticalGrid(
        modifier = Modifier.testTag(stringResource(id = R.string.currencies_grid)),
        columns = GridCells.Adaptive(minSize = 120.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(currenciesConverted) {
            CurrencyGridItem(it)
        }
    }
}