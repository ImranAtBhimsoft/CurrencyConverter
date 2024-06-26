package com.android.currencyconverter.modules

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.android.currencyconverter.BuildConfig
import com.android.currencyconverter.database.AppDatabase
import com.android.currencyconverter.database.CurrencyDao
import com.android.currencyconverter.database.RateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "currency_converter"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCurrencyDao(db: AppDatabase): CurrencyDao = db.currencyDao()

    @Singleton
    @Provides
    fun provideCountryDao(db: AppDatabase): RateDao = db.rateDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            BuildConfig.APPLICATION_ID + "_pref_currency_app",
            Activity.MODE_PRIVATE
        )
}