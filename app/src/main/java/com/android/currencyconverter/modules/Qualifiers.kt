package com.android.currencyconverter.modules

import javax.inject.Qualifier

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkDataSource