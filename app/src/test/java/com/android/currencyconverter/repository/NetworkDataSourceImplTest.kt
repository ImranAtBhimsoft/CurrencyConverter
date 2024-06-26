package com.android.currencyconverter.repository

import com.android.currencyconverter.data.RequestResult
import com.android.currencyconverter.modules.NetworkDataSource
import com.android.currencyconverter.network.Api
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
class NetworkDataSourceImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @NetworkDataSource
    @Inject
    lateinit var dataSource: DataSource

    @Inject
    lateinit var api: Api

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `get currencies test`() = runBlocking {
        val result = dataSource.getCurrencies()
        assertTrue(result is RequestResult.Success)
    }

    @Test
    fun `get rates test`() = runBlocking {
        val result = dataSource.getRates()
        assertTrue(result is RequestResult.Success)
    }

    @Test
    fun `get currencies from API`() = runBlocking {
        val result = api.getCurrencies(dataSource.getDefaultQueryParams())
        assertTrue(result.isSuccessful)
    }

    @Test
    fun `get rates from API`() = runBlocking {
        val result = api.getLatestRates(dataSource.getDefaultQueryParams())
        assertTrue(result.isSuccessful)
    }
}