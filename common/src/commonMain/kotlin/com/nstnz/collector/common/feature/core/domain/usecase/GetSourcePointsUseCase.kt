package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourcePointsUseCase(
    private val currenciesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(
        originCurrency: String,
        sum: Double,
        currency: String
    ) = withContext(dispatcher) {
        currenciesRepository.getHistoricalRatesForSum(originCurrency, sum, currency)
    }
}