package com.android.currencyconverter.modules

import com.android.currencyconverter.repository.DataSource
import com.android.currencyconverter.repository.LocalDataSourceImpl
import com.android.currencyconverter.repository.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 23/12/2023.
 * se.muhammadimran@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @LocalDataSource
    @Binds
    abstract fun localDataSourceBind(localDataSourceImpl: LocalDataSourceImpl): DataSource

    @NetworkDataSource
    @Binds
    abstract fun networkDataSourceBind(networkDataSourceImpl: NetworkDataSourceImpl): DataSource
}