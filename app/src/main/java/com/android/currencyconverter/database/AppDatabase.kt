package com.android.currencyconverter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.currencyconverter.data.Currency
import com.android.currencyconverter.data.Rate

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */

/**
 * Application Room database with two entities
 * @see Rate and
 * @see Currency
 */
@Database(entities = [Rate::class, Currency::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun rateDao(): RateDao
}