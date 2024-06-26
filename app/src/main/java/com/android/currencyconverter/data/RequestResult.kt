package com.android.currencyconverter.data

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 24/12/2023.
 * se.muhammadimran@gmail.com
 */
sealed interface RequestResult<T> {
    class Loading<T> : RequestResult<T>
    data class Success<T>(val data: T) : RequestResult<T>
    sealed class Error<T> : RequestResult<T> {
        data class NoData<T>(val message: String) : Error<T>()
        data class General<T>(val message: String) : Error<T>()
    }
}