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

    private var currencies: MutableMap<String, CurrencyEntity> = mutableMapOf()

    suspend fun getRatesForSum(originCurrency: String, sum: Double, currencies: List<String>) =
        currenciesNetworkDataSource.getRatesForSum(originCurrency, sum, currencies)

    suspend fun getSupportedCurrencies() =
        currencies.values.ifEmpty {
            currenciesDbDataSource.getAllCurrencies().also {
                currencies = it.associateBy { it.code }.toMutableMap()
            }
            currencies.values
        }

    suspend fun refreshSupportedCurrencies() {
        currencies = mutableMapOf()
        currenciesNetworkDataSource.getSupportedCurrencies().forEach { model ->
            currenciesDbDataSource.saveCurrency(model)
        }
    }

    suspend fun getDefaultCurrencyCode() = currenciesPrefs.defaultCurrencyCode

    suspend fun setDefaultCurrencyCode(code: String) {
        currenciesPrefs.defaultCurrencyCode = code
        val curr = getCurrencyByCode(code)
        curr?.let {
            saveCurrency(
                code = code,
                isFavourite = true,
                curr.name,
                curr.crypto
            )
        }
    }

    suspend fun getFavoriteCurrencies() =
        currenciesDbDataSource.getFavoriteCurrencies()

    suspend fun getCurrencyByCode(code: String?) =
        currenciesDbDataSource.getCurrency(code)

    suspend fun getHistoricalRatesForSum(
        originCurrency: String,
        sum: Double,
        currency: String
    ) = currenciesNetworkDataSource.getHistoricalRatesForSum(originCurrency, sum, currency)

    suspend fun saveCurrency(
        code: String,
        isFavourite: Boolean,
        name: String,
        crypto: Boolean
    ) = currenciesDbDataSource.updateCurrency(
        code, isFavourite, name, crypto
    ).also {
        currencies[code] = CurrencyEntity(
            code, name, crypto, isFavourite
        )
    }
}