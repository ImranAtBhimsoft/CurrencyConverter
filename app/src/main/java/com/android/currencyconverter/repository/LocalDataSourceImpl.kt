package com.android.currencyconverter.repository

import com.android.currencyconverter.data.Currency
import com.android.currencyconverter.data.Rate
import com.android.currencyconverter.data.RequestResult
import com.android.currencyconverter.database.CurrencyDao
import com.android.currencyconverter.database.RateDao
import javax.inject.Inject

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */
class LocalDataSourceImpl @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val rateDao: RateDao
) : DataSource {
    override suspend fun getCurrencies(): RequestResult<List<Currency>> {
        return RequestResult.Success(currencyDao.getAll().sortedBy { it.name })
    }

    override suspend fun getRates(): RequestResult<List<Rate>> {
        return RequestResult.Success(rateDao.getAll())
    }

    override suspend fun getRate(currencyCode: String): Rate? = rateDao.getRate(currencyCode)


    override suspend fun saveCurrencies(currencies: List<Currency>): RequestResult<Boolean> {
        currencyDao.insertAll(currencies)
        return RequestResult.Success(true)
    }

    override suspend fun saveRates(rates: List<Rate>): RequestResult<Boolean> {
        rateDao.insertAll(rates)
        return RequestResult.Success(true)
    }
}