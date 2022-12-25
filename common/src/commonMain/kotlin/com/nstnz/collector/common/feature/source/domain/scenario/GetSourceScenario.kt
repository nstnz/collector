package com.nstnz.collector.common.feature.source.domain.scenario

import com.nstnz.collector.common.feature.converter.domain.usecase.GetExchangeRatesUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetMainCurrencyUseCase
import com.nstnz.collector.common.feature.main.domain.model.SourceFundMainModel
import com.nstnz.collector.common.feature.main.domain.model.SourceMainModel
import com.nstnz.collector.common.feature.source.domain.usecase.GetSourceDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourceScenario(
    private val dispatcher: CoroutineDispatcher,
    private val getSourceDataUseCase: GetSourceDataUseCase,
    private val getMainCurrencyUseCase: GetMainCurrencyUseCase,
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
) {

    suspend operator fun invoke(sourceId: String) = withContext(dispatcher) {
        val defaultCurrency = getMainCurrencyUseCase()
        val source = getSourceDataUseCase(sourceId)?.let {
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

        return@withContext source
    }
}