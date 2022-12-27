package com.nstnz.collector.common.feature.currencies.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class SaveFavoriteCurrenciesUseCase(
    private val currenciesRepository: CurrenciesRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        checked: List<CurrencyDomainModel>,
    ) = withContext(dispatcher) {
        currenciesRepository.getSupportedCurrencies().forEach { curr ->
            val needUpdate = (checked.any { curr.code == it.code } && !curr.isFavourite) ||
                    (checked.none { curr.code == it.code } && curr.isFavourite)

            if (needUpdate) {
                currenciesRepository.saveCurrency(
                    curr.code,
                    checked.any { it.code == it.code },
                    curr.name,
                    curr.crypto
                )
            }
        }
    }
}