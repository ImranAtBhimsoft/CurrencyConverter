package com.android.currencyconverter.data

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 24/12/2023.
 * se.muhammadimran@gmail.com
 */
data class RatesResponse(
    val timestamp:Long, //":1703358028
    val base:String,
    val rates:HashMap<String,Double>//"USD"
)
