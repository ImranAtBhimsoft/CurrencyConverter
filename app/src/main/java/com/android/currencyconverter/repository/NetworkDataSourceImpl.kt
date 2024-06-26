package com.android.currencyconverter.repository

import android.util.Log
import com.android.currencyconverter.data.Currency
import com.android.currencyconverter.data.Rate
import com.android.currencyconverter.data.RequestResult
import com.android.currencyconverter.network.Api
import javax.inject.Inject

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */
class NetworkDataSourceImpl @Inject constructor(private val api: Api) : DataSource {
    override suspend fun getCurrencies(): RequestResult<List<Currency>> = try {
        val response = api.getCurrencies(getDefaultQueryParams())
        if (response.isSuccessful) {
            RequestResult.Success(response.body()?.entries?.map {
                Currency(
                    code = it.key,
                    name = it.value
                )
            }?.sortedBy { it.name } ?: emptyList())
        } else {
            Log.e(">>>>NetworkDS", response.errorBody().toString())
            RequestResult.Error.General(response.errorBody().toString())
        }
    } catch (e: Exception) {
        RequestResult.Error.General(e.message.orEmpty())
    }

    /**
     * Error handling is not implemented yet,
     * as this is just a demo project, we can cover Error handling later on
     */
    override suspend fun getRates(): RequestResult<List<Rate>> = try {
        val response = api.getLatestRates(getDefaultQueryParams())
        if (response.isSuccessful) {
            RequestResult.Success(response.body()?.rates?.entries?.map {
                Rate(
                    code = it.key,
                    value = it.value
                )
            } ?: emptyList())
        } else {
            Log.e(">>>>NetworkDS", response.errorBody().toString())
            RequestResult.Error.General(response.errorBody().toString())
        }
    } catch (e: Exception) {
        RequestResult.Error.General(e.localizedMessage.orEmpty())
    }

    override suspend fun getRate(currencyCode: String): Rate? = null
}