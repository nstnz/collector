package com.nstnz.collector.common.feature.core.domain.scenario

import com.nstnz.collector.common.feature.core.domain.model.CurrencySumDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceCountDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetExchangeRatesUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.model.SourceFundEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourceCountScenario(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
) {

    suspend operator fun invoke(
        sourceFundId: String,
        sourceCurrencyCode: String?
    ) = withContext(dispatcher) {
        sourcesRepository.getSourceFund(sourceFundId)?.let {
            invoke(it, sourceCurrencyCode)
        }
    }

    suspend operator fun invoke(
        sourceCount: SourceFundEntity,
        sourceCurrencyCode: String?
    ) = withContext(dispatcher) {
        val currencies = getFavoriteCurrenciesUseCase().map { it.code }.toMutableList().apply {
            sourceCurrencyCode?.let {
                this.add(sourceCurrencyCode)
            }
        }
        sourceCount.let {
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
                    originalCurrency.code, it.sum, currencies
                ),
            )
        }
    }
}