package com.android.currencyconverter

import com.android.currencyconverter.data.Currency
import com.android.currencyconverter.data.Rate

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 24/12/2023.
 * se.muhammadimran@gmail.com
 */
fun getFakeCurrenciesJson() = "{\n" +
        "  \"USD\": \"United States Dollar\",\n" +
        "  \"PKR\": \"Pakistani Rupee\"" +
        "  \"VEF\": \"Venezuelan Bolívar Fuerte (Old)\"" +
        "  \"VES\": \"Venezuelan Bolívar Soberano\"" +
        "}"

fun getFakeRatesJson() = "{\n" +
        "\"USD\": 1,\n" +
        "\"PKR\": 278.955767,\n" +
        "\"VES\": 35.706809\n" +
        "}"

fun fakeCurrencies() = mutableListOf<Currency>().apply {
    add(Currency("USD", "United States Dollar"))
    add(Currency("PKR", "Pakistani Rupee"))
    add(Currency("VES", "Venezuelan Bolívar Soberano"))
}

fun fakeRates() = mutableListOf<Rate>().apply {
    add(Rate("USD", 1.0))
    add(Rate("PKR", 10.0))
    add(Rate("VES", 20.0))
}