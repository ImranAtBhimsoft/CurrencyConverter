package com.android.currencyconverter.data

import java.math.BigDecimal

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 24/12/2023.
 * se.muhammadimran@gmail.com
 *
 * It is a simple data class used to hold converted
 * @param rate
 */
data class CurrencyConverted(
    val code: String,
    val name: String,
    /**
     * Rate is based on USD
     * e.g 1 USD = 278.45 PKR
     */
    val rate: BigDecimal
)
