package com.android.currencyconverter.components

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.currencyconverter.R
import com.android.currencyconverter.data.Currency

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 21/12/2023.
 * se.muhammadimran@gmail.com
 */

/**
 * @param currencies is a list of
 * @see Currency, it also take
 * @param onItemClicked , return back
 * @see Currency
 */
@Composable
fun CurrenciesList(currencies: List<Currency>, onItemClicked: (Currency) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.testTag(stringResource(id = R.string.currency_list))
    ) {
        items(currencies) {
            CurrencyListItem(currency = it, onItemClicked = {
                Log.d(">>>>>CountryList", "OnClicked $it")
                onItemClicked.invoke(it)
            })
        }
    }
}