package com.android.currencyconverter.network

import com.android.currencyconverter.data.RatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */
interface Api {
    @GET("/api/latest.json")
    suspend fun getLatestRates(@QueryMap param: HashMap<String, Any>): Response<RatesResponse>

    @GET("/api/currencies.json")
    suspend fun getCurrencies(@QueryMap param: HashMap<String, Any>): Response<HashMap<String, String>>
}