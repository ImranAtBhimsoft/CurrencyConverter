package com.android.currencyconverter.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.currencyconverter.data.Currency
import com.android.currencyconverter.data.CurrencyConverted
import com.android.currencyconverter.data.RequestResult
import com.android.currencyconverter.modules.LocalDataSource
import com.android.currencyconverter.modules.NetworkDataSource
import com.android.currencyconverter.repository.DataSource
import com.android.currencyconverter.utils.asMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */
@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    //dataSourcProvder
    @LocalDataSource private val localDataSource: DataSource,
    @NetworkDataSource private val networkDataSource: DataSource,
    private var preferences: SharedPreferences
) : ViewModel() {
    companion object {
        private const val TAG = ">>>>VM"
    }


    private val _currencies = MutableStateFlow(listOf<Currency>())
    val currencies: StateFlow<List<Currency>>
        get() = _currencies


    private val _selectedCurrency = MutableStateFlow(Currency("YYY", ""))
    val mSelectedCurrency: StateFlow<Currency>
        get() = _selectedCurrency

    private val _convertedRates =
        MutableStateFlow<List<CurrencyConverted>>(listOf())
    val convertedRates: StateFlow<List<CurrencyConverted>>
        get() = _convertedRates

    init {
        loadCurrenciesLocally()
        if (needToSync()) {
            syncData()
        }
    }

    fun convertCurrency(currency: Currency, valueToConvert: Double) {
        Log.d(TAG, "country: $currency and valueToConvert: $valueToConvert")
        if (valueToConvert == 0.0) {
            _convertedRates.update { emptyList() }
            return
        }
        viewModelScope.launch {
            when (val result =
                localDataSource.getConversion(currency.code, valueToConvert)) {
                is RequestResult.Error -> {
                    if (result is RequestResult.Error.NoData) {
                        syncData()
                    }
                }

                is RequestResult.Success -> {
                    _convertedRates.update { result.data }
                }

                else -> {
                    // TODO: can handle loading here
                }
            }
        }
    }

    fun loadCurrenciesLocally() {
        viewModelScope.launch {
            val result = localDataSource.getCurrencies()
            if (result is RequestResult.Success)
                _currencies.update { result.data }
        }
    }

    fun syncData() {
        Log.d(TAG, "syncData()")
        viewModelScope.launch {
            val ratesResponse = async { networkDataSource.getRates() }
            val currenciesResponse = async { networkDataSource.getCurrencies() }
            val rates = ratesResponse.await()
            val currencies = currenciesResponse.await()
            Log.d(TAG, "syncData()->rates : $rates")
            Log.d(TAG, "syncData()->currencies : $currencies")
            if (rates is RequestResult.Success && currencies is RequestResult.Success) {
                localDataSource.saveRates(rates.data)
                val map = rates.data.asMap()
                val excludedList = currencies.data.filter {
                    map.containsKey(it.code)
                }
                localDataSource.saveCurrencies(excludedList)
                _currencies.update { excludedList }

                preferences.edit {
                    putLong("last_sync", System.currentTimeMillis())
                }
            }
        }
    }

    fun needToSync(): Boolean {
        val elapseTime = 30 * 60 * 1000
        val lastSync = preferences.getLong("last_sync", 0)
        Log.d(TAG, "Last Sync at lastSync =$lastSync")
        return lastSync + elapseTime < System.currentTimeMillis()
    }

    fun updateSelectedCurrency(rate: Currency) {
        _selectedCurrency.update { rate }
    }
}