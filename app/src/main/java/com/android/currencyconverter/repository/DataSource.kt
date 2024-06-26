package com.android.currencyconverter.repository

import com.android.currencyconverter.BuildConfig
import com.android.currencyconverter.data.Currency
import com.android.currencyconverter.data.CurrencyConverted
import com.android.currencyconverter.data.Rate
import com.android.currencyconverter.data.RequestResult
import java.math.RoundingMode

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */
interface DataSource {
    suspend fun getCurrencies(): RequestResult<List<Currency>>
    suspend fun getRates(): RequestResult<List<Rate>>
    suspend fun getRate(currencyCode: String): Rate?


    suspend fun saveCurrencies(currencies: List<Currency>): RequestResult<Boolean> {
        return RequestResult.Success(true)
    }

    suspend fun saveRates(rates: List<Rate>): RequestResult<Boolean> {
        return RequestResult.Success(true)
    }

    /**
     * 1 USD = 200 PKR
     * 1 USD  = 220 INR
     * 1 USD = 1.47 AUD
     *
     * 100 AUD to USD ?
     * 1 AUD = 0.68 USD
     * 1 /value from DB * input == USD -- for US
     *
     * For PAK
     * 1 /value from DB * input = USD
     *
     * USD * PKR DB value
     * Rates
     * VES - YES
     * VEF - No
     */
    suspend fun getConversion(
        currencyCode: String,
        valueToConvert: Double
    ): RequestResult<List<CurrencyConverted>> {
        val currencies = (getCurrencies() as RequestResult.Success).data
        val rates = (getRates() as RequestResult.Success).data.associate {
            it.code to it.value
        }
        val selectedRate: Rate? = getRate(currencyCode)
        return getConvertedCurrencies(valueToConvert, selectedRate, currencies, rates)
    }

    fun getConvertedCurrencies(
        valueToConvert: Double,
        selectedRate: Rate?,
        currencies: List<Currency>,
        rates: Map<String, Double>
    ): RequestResult<List<CurrencyConverted>> {
        return selectedRate?.let { sR ->
            val factor = valueToConvert / sR.value
            RequestResult.Success(currencies.map {
                val rate = rates[it.code] ?: 0.0
                val result = (factor * rate).toBigDecimal()
                    .setScale(4, RoundingMode.UP)
                CurrencyConverted(it.code, it.name, result)
            }.sortedBy { it.code })
        } ?: RequestResult.Error.NoData("Selected Rate is Null")
    }

    fun getDefaultQueryParams(): HashMap<String, Any> {
        return hashMapOf<String, Any>(
            "prettyprint" to false,
            "show_alternative" to false,
            "show_inactive" to false,
            "app_id" to BuildConfig.API_KEY,
        )
    }
}