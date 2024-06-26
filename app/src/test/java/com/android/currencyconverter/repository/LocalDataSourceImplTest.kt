package com.android.currencyconverter.repository

import com.android.currencyconverter.data.Currency
import com.android.currencyconverter.data.RequestResult
import com.android.currencyconverter.fakeCurrencies
import com.android.currencyconverter.fakeRates
import com.android.currencyconverter.modules.LocalDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 24/12/2023.
 * se.muhammadimran@gmail.com
 */
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = Config.NONE,
    application = HiltTestApplication::class,
)
class LocalDataSourceImplTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @LocalDataSource
    @Inject
    lateinit var dataSource: DataSource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `get currencies test`() = runBlocking {
        dataSource.saveCurrencies(fakeCurrencies())
        val currencies = dataSource.getCurrencies()
        assertTrue(currencies is RequestResult.Success)
    }

    @Test
    fun `get rates test`() = runBlocking {
        dataSource.saveRates(fakeRates())
        val rates = dataSource.getRates()
        assertTrue(rates is RequestResult.Success)
    }

    @Test
    fun `get conversion test`() {
        runBlocking {
            val fakeCurrencies = mutableListOf<Currency>().apply {
                add(Currency("USD", "United States Dollar"))
            }
            val fakeRates = fakeRates()
            dataSource.saveCurrencies(fakeCurrencies)
            dataSource.saveRates(fakeRates)

            val selectedCurrency = fakeCurrencies[0]
            val valueToConvert = 10.0
            val expected = 10.0
            val conversions = dataSource.getConversion(selectedCurrency.code, valueToConvert)
            assertTrue(conversions is RequestResult.Success)
            val data = (conversions as RequestResult.Success).data[0]
            assertTrue(data.rate.toDouble() == expected)
        }
    }

    @Test
    fun `save currencies test`() = runBlocking {
        val fakeCurrencies = fakeCurrencies()
        dataSource.saveCurrencies(fakeCurrencies)
        val currencies = dataSource.getCurrencies()
        assertTrue(currencies is RequestResult.Success)
        assertTrue((currencies as RequestResult.Success).data.size == fakeCurrencies.size)
    }

    @Test
    fun `save rates test`() = runBlocking {
        val fakeRates = fakeRates()
        dataSource.saveRates(fakeRates)
        val rates = dataSource.getRates()
        assertTrue(rates is RequestResult.Success)
        assertTrue((rates as RequestResult.Success).data.size == fakeRates.size)
    }
}