package com.android.currencyconverter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 21/12/2023.
 * se.muhammadimran@gmail.com
 */
@Entity
data class Rate(
    @PrimaryKey
    val code: String,
    /**
     * This value is again 1 USD
     * e.g $1USD =3.672345 AED
     */
    val value: Double
)
