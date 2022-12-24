package com.nstnz.collector.common.feature.currencies.data

import com.nstnz.collector.common.feature.currencies.data.db.datasource.CurrenciesDbDataSource
import com.nstnz.collector.common.feature.currencies.data.network.datasource.CurrenciesNetworkDataSource

internal class ExchangeRatesRepository(
    private val currenciesNetworkDataSource: CurrenciesNetworkDataSource,
    private val currenciesDbDataSource: CurrenciesDbDataSource,
) {

    suspend fun getRatesForSum(originCurrency: String, sum: Float, currencies: List<String>) =
        currenciesNetworkDataSource.getRatesForSum(originCurrency, sum, currencies)

    suspend fun getSupportedCurrencies() =
        currenciesDbDataSource.getAllCurrencies()

    suspend fun refreshSupportedCurrencies() {
        currenciesNetworkDataSource.getSupportedCurrencies().forEach { model ->
            currenciesDbDataSource.saveCurrency(model)
        }
    }
}