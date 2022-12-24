package com.nstnz.collector.common.feature.currencies.domain.usecase

import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetCurrenciesUseCase(
    private val exchangeRatesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        exchangeRatesRepository.getSupportedCurrencies()
    }
}