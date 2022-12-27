package com.nstnz.collector.common.feature.core.domain.scenario

import com.nstnz.collector.common.feature.core.domain.model.SourcesListDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourcesListScenario(
    private val dispatcher: CoroutineDispatcher,
    private val getSourcesDataUseCase: GetSourcesScenario,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        val favoriteCurrencies = getFavoriteCurrenciesUseCase()
        val sources = getSourcesDataUseCase()

        return@withContext SourcesListDomainModel(
            sources = sources,
            favoriteCurrencies = favoriteCurrencies
        )
    }
}