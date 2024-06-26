package com.android.currencyconverter.database


import android.content.SharedPreferences
import androidx.core.content.edit
import com.android.currencyconverter.fakeCurrencies
import com.android.currencyconverter.fakeRates
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
class AppDatabaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDB: AppDatabase

    @Inject
    lateinit var sp: SharedPreferences
    private lateinit var currencyDao: CurrencyDao
    private lateinit var ratesDao: RateDao


    @Before
    fun `set up`() {
        hiltRule.inject()
        currencyDao = appDB.currencyDao()
        ratesDao = appDB.rateDao()
    }

    @Test
    fun `test currency operations`() = runBlocking {
        val currencies = fakeCurrencies()
        currencyDao.insertAll(currencies)
        val savedCurrencies = currencyDao.getAll()
        val currency = currencyDao.getCurrency(currencies[0].code)
        assertEquals(currencies.size, savedCurrencies.size)
        assertNotNull(currency)
        assertEquals(currencies[0].code, currency?.code)
    }

    @Test
    fun `test rate operations`() = runBlocking {
        val rates = fakeRates()
        ratesDao.insertAll(rates)
        val savedRates = ratesDao.getAll()
        val rate = ratesDao.getRate(rates[0].code)
        assertEquals(rates.size, savedRates.size)
        assertNotNull(rate)
        assertEquals(rates[0].code, rate?.code)
    }

    @Test
    fun `test shared preferences`() = runBlocking{
        with(sp) {
            edit {
                val value = "1234"
                putString("key", value)
                val expected = getString("key", value)
                assertEquals(value, expected)
            }
        }
    }

    @After
    fun `release resources`() {
        appDB.close()
    }
}