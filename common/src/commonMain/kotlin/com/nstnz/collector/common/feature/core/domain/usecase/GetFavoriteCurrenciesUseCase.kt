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
        val currency = currenciesRepository.getDefaultCurrency() ?: CurrencyEntity(
            "USD", "", false, true
        )
        listOf(
            CurrencyDomainModel(
                code = currency.code,
                name = currency.name,
                crypto = currency.crypto,
                isFavourite = currency.isFavourite
            )
        )
    }
}