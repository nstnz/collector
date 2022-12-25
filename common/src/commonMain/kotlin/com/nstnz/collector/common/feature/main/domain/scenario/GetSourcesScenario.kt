package com.nstnz.collector.common.feature.main.domain.scenario

import com.nstnz.collector.common.feature.converter.domain.usecase.GetExchangeRatesUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetMainCurrencyUseCase
import com.nstnz.collector.common.feature.main.domain.model.SourceFundMainModel
import com.nstnz.collector.common.feature.main.domain.model.SourceMainModel
import com.nstnz.collector.common.feature.main.domain.model.SourcesMainModel
import com.nstnz.collector.common.feature.main.domain.usecase.GetSourcesDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourcesScenario(
    private val dispatcher: CoroutineDispatcher,
    private val getSourcesDataUseCase: GetSourcesDataUseCase,
    private val getMainCurrencyUseCase: GetMainCurrencyUseCase,
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        val defaultCurrency = getMainCurrencyUseCase()
        val sources = getSourcesDataUseCase().map {
            SourceMainModel(
                id = it.id,
                name = it.name,
                defaultCurrency = defaultCurrency,
                funds = it.funds.map {
                    SourceFundMainModel(
                        id = it.id,
                        originalSum = it.sum,
                        originalCurrency = it.currencyCode,
                        defaultCurrency = defaultCurrency,
                        sum = getExchangeRatesUseCase(
                            originCurrency = it.currencyCode,
                            sum = it.sum,
                            currencies = listOf(defaultCurrency.code)
                        ).first().sum
                    )
                }
            )
        }

        return@withContext SourcesMainModel(
            currency = defaultCurrency,
            sources = sources
        )
    }
}