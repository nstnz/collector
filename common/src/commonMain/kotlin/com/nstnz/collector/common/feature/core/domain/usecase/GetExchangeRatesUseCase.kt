package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencySumDomainModel
import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetExchangeRatesUseCase(
    private val currenciesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val getCurrencyUseCase: GetCurrencyUseCase,
) {

    suspend operator fun invoke(
        originCurrencyCode: String,
        sum: Double,
        currencies: List<String>
    ): List<CurrencySumDomainModel> = withContext(dispatcher) {
        currenciesRepository.getRatesForSum(
            originCurrency = originCurrencyCode,
            sum = sum,
            currencies = currencies
        ).map {
            CurrencySumDomainModel(
                sum = it.sum,
                currency = getCurrencyUseCase(it.code)
            )
        }
    }
}