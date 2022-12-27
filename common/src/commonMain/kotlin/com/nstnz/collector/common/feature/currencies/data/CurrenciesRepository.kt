package com.nstnz.collector.common.feature.currencies.data

import com.nstnz.collector.common.feature.currencies.data.db.datasource.CurrenciesDbDataSource
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.currencies.data.network.datasource.CurrenciesNetworkDataSource
import com.nstnz.collector.common.feature.currencies.data.prefs.CurrenciesPrefs

internal class CurrenciesRepository(
    private val currenciesNetworkDataSource: CurrenciesNetworkDataSource,
    private val currenciesDbDataSource: CurrenciesDbDataSource,
    private val currenciesPrefs: CurrenciesPrefs,
) {

    private var currencies: List<CurrencyEntity> = emptyList()

    suspend fun getRatesForSum(originCurrency: String, sum: Double, currencies: List<String>) =
        currenciesNetworkDataSource.getRatesForSum(originCurrency, sum, currencies)

    suspend fun getSupportedCurrencies() =
        currencies.ifEmpty {
            currenciesDbDataSource.getAllCurrencies().also {
                currencies = it
            }
        }

    suspend fun refreshSupportedCurrencies() {
        currencies = emptyList()
        currenciesNetworkDataSource.getSupportedCurrencies().forEach { model ->
            currenciesDbDataSource.saveCurrency(model)
        }
    }

    suspend fun getDefaultCurrencyCode() = currenciesPrefs.defaultCurrencyCode

    suspend fun getDefaultCurrency() =
        currenciesDbDataSource.getCurrency(currenciesPrefs.defaultCurrencyCode)

    suspend fun getCurrencyByCode(code: String?) =
        currenciesDbDataSource.getCurrency(code)
}