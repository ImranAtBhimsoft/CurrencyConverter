package com.android.currencyconverter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.currencyconverter.data.Currency

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */

/**
 * Currency Dao based on
 * @see Currency entity
 */
@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    suspend fun getAll(): List<Currency>

    @Query("SELECT * FROM currency WHERE code = :currencyCode")
    suspend fun getCurrency(currencyCode: String): Currency?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<Currency>)
}