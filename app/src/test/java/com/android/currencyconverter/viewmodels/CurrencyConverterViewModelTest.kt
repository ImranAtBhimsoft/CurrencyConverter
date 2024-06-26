package com.android.currencyconverter.viewmodels

import android.content.SharedPreferences
import androidx.core.content.edit
import com.android.currencyconverter.MainDispatcherRule
import com.android.currencyconverter.data.Currency
import com.android.currencyconverter.data.RequestResult
import com.android.currencyconverter.fakeRates
import com.android.currencyconverter.repository.DataSource
import com.android.currencyconverter.utils.asMap
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
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
class CurrencyConverterViewModelTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Inject
    lateinit var sp: SharedPreferences

    private var localDataSource: DataSource = FakeLocalDataSource()
    private var networkDataSource: DataSource = FakeNetworkDataSource()

    private lateinit var viewModel: CurrencyConverterViewModel

    @Before
    fun `set up`() {
        hiltRule.inject()
        viewModel = CurrencyConverterViewModel(localDataSource, networkDataSource, sp)
    }

    @Test
    fun `load currencies locally test`() = runTest {
        viewModel.loadCurrenciesLocally()
        val expected: List<Currency> = viewModel.currencies.value
        assertTrue(expected.isNotEmpty())
    }

    @Test
    fun getMSelectedRate() {
    }

    @Test
    fun `get converted rates test`() = runTest {
        val currencies = (localDataSource.getCurrencies() as RequestResult.Success).data
        val selectedCurrency = currencies[0]
        val expectedResult = fakeRates().asMap()[selectedCurrency.code]!! * 10.0
        viewModel.convertCurrency(selectedCurrency, 10.0)
        val result = viewModel.convertedRates.value
        val actual = result.find { it.code == selectedCurrency.code }
        assertTrue(result.isNotEmpty())
        assertEquals(actual?.rate?.toDouble(), expectedResult)
        viewModel.convertCurrency(selectedCurrency, 0.0)
        assertTrue(viewModel.convertedRates.value.isEmpty())
    }

    @Test
    fun convertCurrency() {
    }

    @Test
    fun `sync data test`() = runTest {
        viewModel.syncData()
        assertTrue(viewModel.currencies.value.isNotEmpty())
    }

    @Test
    fun `need to sync test`() = runTest {
        with(sp) {
            val elapsedTime: Long = 30 * 60 * 1000
            val toSave = System.currentTimeMillis() - elapsedTime
            edit {
                putLong("last_sync", toSave)
            }
            delay(1000)
            assertTrue(viewModel.needToSync())
            edit {
                putLong("last_sync", System.currentTimeMillis())
            }
            assertTrue(!viewModel.needToSync())
        }
    }

    @Test
    fun `update Selected currency test`() = runTest {
        val currency = Currency("PKR", "Pakistani Rupee")
        val job = backgroundScope.launch {
            viewModel.mSelectedCurrency.collect()
        }
        viewModel.updateSelectedCurrency(currency)
        val expected: Currency = viewModel.mSelectedCurrency.value
        assertNotNull(expected)
        assertEquals(expected.code, currency.code)
        job.cancel()
    }
}