package com.android.currencyconverter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */
@Entity
data class Currency(
    @PrimaryKey
    val code: String,
    val name: String
)
