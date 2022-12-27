package com.nstnz.collector.common.feature.core.domain.scenario

import com.nstnz.collector.common.feature.core.domain.model.SourceDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourceScenario(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase,
    private val getSourceCountsUseCase: GetSourceCountsScenario,
    private val getCurrencyUseCase: GetCurrencyUseCase,
) {

    suspend operator fun invoke(
        sourceId: String,
    ): SourceDomainModel? = withContext(dispatcher) {
        sourcesRepository.getSource(sourceId)?.let {
            invoke(it)
        }
    }

    suspend operator fun invoke(
        source: SourceEntity,
    ): SourceDomainModel = withContext(dispatcher) {
        val favoriteCurrencies = getFavoriteCurrenciesUseCase()
        source.let {
            SourceDomainModel(
                id = it.id,
                name = it.name,
                originalCurrency = getCurrencyUseCase(it.currencyCode),
                favoriteCurrencies = favoriteCurrencies,
                counts = getSourceCountsUseCase(
                    it.id
                )
            )
        }
    }
}