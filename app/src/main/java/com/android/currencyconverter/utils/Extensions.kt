package com.android.currencyconverter.utils

import com.android.currencyconverter.data.Rate

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 25/12/2023.
 * se.muhammadimran@gmail.com
 */
fun List<Rate>.asMap(): Map<String, Double> {
    return this.associate { it.code to it.value }
}