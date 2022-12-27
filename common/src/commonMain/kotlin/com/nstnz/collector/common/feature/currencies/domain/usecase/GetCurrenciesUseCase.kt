package com.nstnz.collector.common.feature.currencies.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetCurrenciesUseCase(
    private val exchangeRatesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        exchangeRatesRepository.getSupportedCurrencies()
            .sortedBy { it.code }
            .sortedBy { it.crypto }
            .sortedByDescending { it.isFavourite }
            .map {
                CurrencyDomainModel(
                    code = it.code,
                    name = it.name,
                    isFavourite = it.isFavourite,
                    crypto = it.crypto
                )
            }
    }
}