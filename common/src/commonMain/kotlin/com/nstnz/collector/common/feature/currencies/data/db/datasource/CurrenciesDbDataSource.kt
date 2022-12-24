package com.nstnz.collector.common.feature.currencies.data.db.datasource

import com.nstnz.collector.AppDatabaseQueries
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.currencies.data.network.model.CurrencyModelDto

internal class CurrenciesDbDataSource(
    private val queries: AppDatabaseQueries
) {

    internal fun getAllCurrencies(): List<CurrencyEntity> {
        return queries.getAllCurrencies(::mapCurrency).executeAsList()
    }

    internal fun saveCurrency(currencyModelDto: CurrencyModelDto) {
        queries.insertCurrency(
            code = currencyModelDto.code,
            name = currencyModelDto.name,
            crypto = currencyModelDto.crypto
        )
    }

    private fun mapCurrency(
        code: String,
        name: String?,
        crypto: Boolean?,
    ): CurrencyEntity {
        return CurrencyEntity(
            code = code,
            name = name.orEmpty(),
            crypto = crypto ?: false,
        )
    }
}