package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class SaveDefaultCurrencyUseCase(
    private val currenciesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(currencyDomainModel: CurrencyDomainModel) =
        invoke(currencyDomainModel.code)

    suspend operator fun invoke(code: String) =
        withContext(dispatcher) {
            currenciesRepository.setDefaultCurrencyCode(code)
        }
}