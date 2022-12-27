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
            crypto = currencyModelDto.crypto,
            isFavourite = currencyModelDto.code == "USD"
        )
    }

    internal fun updateCurrency(
        code: String,
        isFavourite: Boolean,
        name: String,
        crypto: Boolean
    ) {
        queries.insertCurrency(
            code = code,
            name = name,
            crypto = crypto,
            isFavourite = isFavourite
        )
    }

    fun getCurrency(code: String?): CurrencyEntity? {
        return queries.getCurrency(code.orEmpty(), ::mapCurrency).executeAsOneOrNull()
    }

    fun getFavoriteCurrencies(): List<CurrencyEntity> {
        return queries.getFavoriteCurrencies( ::mapCurrency).executeAsList()
    }

    private fun mapCurrency(
        code: String,
        name: String?,
        crypto: Boolean?,
        isFavourite: Boolean?
    ): CurrencyEntity {
        return CurrencyEntity(
            code = code,
            name = name.orEmpty(),
            crypto = crypto ?: false,
            isFavourite = isFavourite ?: false
        )
    }
}