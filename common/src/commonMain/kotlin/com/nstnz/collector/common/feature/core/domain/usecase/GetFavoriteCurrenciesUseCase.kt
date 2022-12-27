package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetFavoriteCurrenciesUseCase(
    private val currenciesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(): List<CurrencyDomainModel> = withContext(dispatcher) {
        val default = currenciesRepository.getDefaultCurrencyCode()
        currenciesRepository.getFavoriteCurrencies().map {
            CurrencyDomainModel(
                code = it.code,
                name = it.name,
                crypto = it.crypto,
                isFavourite = it.isFavourite
            )
        }.sortedByDescending { it.code == default }
    }
}