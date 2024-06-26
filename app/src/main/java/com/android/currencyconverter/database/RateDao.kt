package com.android.currencyconverter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.currencyconverter.data.Rate

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */

/**
 * Rate Dao based on
 * @see Rate entity
 */
@Dao
interface RateDao {
    @Query("SELECT * FROM rate")
    suspend fun getAll(): List<Rate>

    @Query("SELECT * FROM rate WHERE code = :currencyCode")
    suspend fun getRate(currencyCode: String): Rate?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rates: List<Rate>)
}