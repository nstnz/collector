package com.nstnz.collector.common.feature.converter.domain.usecase

import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetExchangeRatesUseCase(
    private val currenciesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(
        originCurrency: CurrencyEntity,
        sum: Float,
        currencies: List<CurrencyEntity>
    ) = withContext(dispatcher) {
        currenciesRepository.getRatesForSum(
            originCurrency = originCurrency.code,
            sum = sum,
            currencies = currencies.map { it.code }
        )
    }

    suspend operator fun invoke(
        originCurrency: String,
        sum: Float,
        currencies: List<String>
    ) = withContext(dispatcher) {
        currenciesRepository.getRatesForSum(
            originCurrency = originCurrency,
            sum = sum,
            currencies = currencies
        )
    }
}