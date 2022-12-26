package com.nstnz.collector.common.feature.core.domain.scenario

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourcesListDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetSourcesDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourcesListScenario(
    private val dispatcher: CoroutineDispatcher,
    private val getSourcesDataUseCase: GetSourcesDataUseCase,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase,
) {

    suspend operator fun invoke(
        selectedCurrencies: List<CurrencyDomainModel>? = null
    ) = withContext(dispatcher) {
        val favoriteCurrencies = getFavoriteCurrenciesUseCase()
        val sources = getSourcesDataUseCase()

        return@withContext SourcesListDomainModel(
            sources = sources,
            selectedCurrencies = selectedCurrencies.orEmpty(),
            favoriteCurrencies = favoriteCurrencies
        )
    }
}