package com.nstnz.collector.common.feature.core.domain.scenario

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetSourceCountsUseCase
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourceScenario(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase,
    private val getSourceCountsUseCase: GetSourceCountsUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
) {

    suspend operator fun invoke(
        sourceId: String,
        selectedCurrencies: List<CurrencyDomainModel>? = null
    ): SourceDomainModel? = withContext(dispatcher) {
        val favoriteCurrencies = getFavoriteCurrenciesUseCase()
        sourcesRepository.getSource(sourceId)?.let {
            SourceDomainModel(
                id = it.id,
                name = it.name,
                originalCurrency = getCurrencyUseCase(it.currencyCode),
                favoriteCurrencies = favoriteCurrencies,
                selectedCurrencies = selectedCurrencies.orEmpty(),
                counts = getSourceCountsUseCase(
                    it.id, selectedCurrencies
                )
            )
        }
    }
}