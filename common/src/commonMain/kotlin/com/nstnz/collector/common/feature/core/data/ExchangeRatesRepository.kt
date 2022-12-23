package com.nstnz.collector.common.feature.core.data

import com.nstnz.collector.common.feature.core.data.network.datasource.ExchangeRatesDataSource

internal class ExchangeRatesRepository(
    private val exchangeRatesDataSource: ExchangeRatesDataSource
) {

    suspend fun getRatesForSum(originCurrency: String, sum: Float, currencies: List<String>) =
        exchangeRatesDataSource.getRatesForSum(originCurrency, sum, currencies)

    suspend fun getSupportedCurrencies() =
        exchangeRatesDataSource.getSupportedCurrencies()
}