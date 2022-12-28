package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetDefaultCurrencyUseCase(
    private val currenciesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        val default = currenciesRepository.getDefaultCurrencyCode()
        return@withContext currenciesRepository.getCurrencyByCode(default)?.let {
            CurrencyDomainModel(
                code = it.code,
                name = it.name,
                crypto = it.crypto,
                isFavourite = it.isFavourite
            )
        }
    }
}