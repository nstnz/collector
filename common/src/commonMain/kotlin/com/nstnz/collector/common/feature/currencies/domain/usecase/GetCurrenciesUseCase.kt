package com.nstnz.collector.common.feature.currencies.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetCurrenciesUseCase(
    private val currenciesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        val default = currenciesRepository.getDefaultCurrencyCode()
        currenciesRepository.getSupportedCurrencies()
            .sortedBy { it.code }
            .sortedBy { it.crypto }
            .sortedByDescending { it.isFavourite }
            .map {
                CurrencyDomainModel(
                    code = it.code,
                    name = it.name,
                    isFavourite = it.isFavourite,
                    crypto = it.crypto,
                    isDefault = default == it.code
                )
            }
    }
}