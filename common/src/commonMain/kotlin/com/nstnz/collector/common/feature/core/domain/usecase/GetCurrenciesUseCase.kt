package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.core.data.ExchangeRatesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetCurrenciesUseCase(
    private val exchangeRatesRepository: ExchangeRatesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        exchangeRatesRepository.getSupportedCurrencies()
    }
}