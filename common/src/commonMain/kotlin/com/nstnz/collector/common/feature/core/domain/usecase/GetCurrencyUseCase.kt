package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetCurrencyUseCase(
    private val currenciesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(code: String?) = withContext(dispatcher) {
        val currency = currenciesRepository.getCurrencyByCode(code)
        currency!!.let {
            CurrencyDomainModel(
                code = currency.code,
                name = currency.name,
                crypto = currency.crypto,
                isFavourite = currency.isFavourite,
                isDefault = currenciesRepository.getDefaultCurrencyCode() == currency.code
            )
        }
    }
}