package com.android.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.currencyconverter.components.TopAppBar
import com.android.currencyconverter.screens.HomeContent
import com.android.currencyconverter.theme.CurrencyConverterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                TopAppBar(
                    content = {
                        HomeContent(it)
                    }
                )
            }
        }
    }
}