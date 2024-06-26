package com.android.currencyconverter.viewmodels

import com.android.currencyconverter.data.Currency
import com.android.currencyconverter.data.Rate
import com.android.currencyconverter.data.RequestResult
import com.android.currencyconverter.fakeCurrencies
import com.android.currencyconverter.fakeRates
import com.android.currencyconverter.repository.DataSource
import com.android.currencyconverter.utils.asMap

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 25/12/2023.
 * se.muhammadimran@gmail.com
 */
class FakeNetworkDataSource : DataSource {
    override suspend fun getCurrencies(): RequestResult<List<Currency>> =
        RequestResult.Success(fakeCurrencies())


    override suspend fun getRates(): RequestResult<List<Rate>> =
        RequestResult.Success(fakeRates())

    override suspend fun getRate(currencyCode: String): Rate? {
        val value = fakeRates().asMap()[currencyCode]
        return value?.let { Rate(currencyCode, it) }
    }
}