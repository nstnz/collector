package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.model.CurrencySumDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceCountDomainModel
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourceCountDataUseCase(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
) {

    suspend operator fun invoke(
        sourceFundId: String,
        selectedCurrencies: List<CurrencyDomainModel>? = null
    ) = withContext(dispatcher) {
        val favoriteCurrencies = getFavoriteCurrenciesUseCase()
        sourcesRepository.getSourceFund(sourceFundId)?.let {
            val originalCurrency = getCurrencyUseCase(it.currencyCode)
            SourceCountDomainModel(
                id = it.id,
                sourceId = it.sourceId,
                isDefault = it.default,
                originalSum = CurrencySumDomainModel(
                    currency = originalCurrency,
                    sum = it.sum
                ),
                favoriteSums = getExchangeRatesUseCase(
                    originalCurrency.code, it.sum, favoriteCurrencies.map { it.code }
                ),
                selectedSums = selectedCurrencies?.let { curr ->
                    getExchangeRatesUseCase(
                        originalCurrency.code, it.sum, curr.map { it.code }
                    )
                } ?: emptyList(),
            )
        }
    }
}