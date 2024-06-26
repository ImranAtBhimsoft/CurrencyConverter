package com.android.currencyconverter.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.currencyconverter.components.BottomSheet
import com.android.currencyconverter.components.CurrenciesGrid
import com.android.currencyconverter.components.CurrenciesList
import com.android.currencyconverter.components.CurrencyEditText
import com.android.currencyconverter.components.TextBody
import com.android.currencyconverter.components.TextHeading
import com.android.currencyconverter.viewmodels.CurrencyConverterViewModel
import java.math.RoundingMode

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 21/12/2023.
 * se.muhammadimran@gmail.com
 */

@Composable
fun HomeContent(innerPadding: PaddingValues, viewModel: CurrencyConverterViewModel = viewModel()) {
    val currenciesConverted by viewModel.convertedRates.collectAsState()
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = innerPadding.calculateTopPadding())
            .fillMaxSize()
    ) {
        Header(viewModel)
        CurrenciesGrid(currenciesConverted = currenciesConverted)
    }
}

@Composable
fun Header(viewModel: CurrencyConverterViewModel) {
    var mExpanded by remember { mutableStateOf(false) }
    var valueToOConvert by remember { mutableStateOf(0.toBigDecimal()) }
    val selectedCountry by viewModel.mSelectedCurrency.collectAsState()
    val countries by viewModel.currencies.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 100.dp)
            .padding(vertical = 16.dp),

        ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterVertically
            ),

            ) {
            TextHeading(
                text = "Enter amount to convert",
            )
            CurrencyEditText(onValueChanged = {
                Log.d(">>>>>Currency", "Final Value is: $it and ${0.toBigDecimal()}")
                valueToOConvert = it
                if (selectedCountry.code != "YYY")
                    viewModel.convertCurrency(
                        selectedCountry,
                        it.setScale(4, RoundingMode.UP).toDouble()
                    )
            })

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedIconButton(
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .size(width = screenWidth / 2, height = 38.dp)
                        .testTag("drop_down"),
                    onClick = { mExpanded = !mExpanded },

                    ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextBody(
                            text = selectedCountry.code,
                        )
                        Icon(imageVector = icon, contentDescription = "Arrow")
                    }

                }
            }

        }
    }

    if (mExpanded) {
        BottomSheet(
            content = {
                CurrenciesList(
                    currencies = countries,
                    onItemClicked = {
                        viewModel.updateSelectedCurrency(it)
                        mExpanded = false
                        if (valueToOConvert > 0.toBigDecimal())
                            viewModel.convertCurrency(
                                it,
                                valueToOConvert.setScale(5, RoundingMode.UP).toDouble()
                            )
                    })
            },
            onDismiss = {
                mExpanded = false
            }
        )
    }
}