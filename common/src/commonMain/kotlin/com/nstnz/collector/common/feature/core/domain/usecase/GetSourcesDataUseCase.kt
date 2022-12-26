package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceDomainModel
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourcesDataUseCase(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getSourceCountsUseCase: GetSourceCountsUseCase
) {

    suspend operator fun invoke(
        selectedCurrencies: List<CurrencyDomainModel>? = null
    ): List<SourceDomainModel> = withContext(dispatcher) {
        val favoriteCurrencies = getFavoriteCurrenciesUseCase()
        val sources = sourcesRepository.getAllSources()

        return@withContext sources.map {
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