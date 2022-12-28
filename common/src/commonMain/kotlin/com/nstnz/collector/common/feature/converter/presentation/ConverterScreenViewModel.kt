package com.nstnz.collector.common.feature.converter.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenIntent
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenState
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetDefaultCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetExchangeRatesUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase

internal class ConverterScreenViewModel(
    private val router: Router,
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase
) : CoroutinesViewModel<ConverterScreenState, ConverterScreenIntent, ConverterScreenSingleEvent>() {

    private var actualCurrency: CurrencyDomainModel? = null

    override fun initialState(): ConverterScreenState = ConverterScreenState(
        sum = "",
        currency = null,
        exchangeList = emptyList()
    )

    override fun reduce(
        intent: ConverterScreenIntent,
        prevState: ConverterScreenState
    ): ConverterScreenState = when (intent) {
        is ConverterScreenIntent.Update -> prevState.copy(
            sum = intent.sum,
            currency = intent.currency,
            exchangeList = intent.exchangeList
        )
        else -> prevState
    }

    override suspend fun performSideEffects(
        intent: ConverterScreenIntent,
        state: ConverterScreenState
    ): ConverterScreenIntent? = when (intent) {
        ConverterScreenIntent.ShowMainScreen -> {
            router.navigateToMainScreen()
            null
        }
        ConverterScreenIntent.ChangeCurrency -> {
            router.navigateToCurrenciesScreen(
                multiCheck = false,
                saveChanges = false,
                currency = state.currency?.code
            )
            null
        }
        ConverterScreenIntent.ShowSettingsScreen -> {
            router.navigateToSettingsScreen()
            null
        }
        is ConverterScreenIntent.ChangeSum -> {
            val favCurrencies = getFavoriteCurrenciesUseCase()
            val exchangeList = getExchangeRatesUseCase(
                originCurrencyCode = state.currency?.code.orEmpty(),
                sum = intent.sum.replace(" ", "").toDoubleOrNull() ?: 0.0,
                currencies = favCurrencies.map { it.code }
            )
            ConverterScreenIntent.Update(
                intent.sum,
                state.currency,
                exchangeList
            )
        }
        ConverterScreenIntent.OnResume -> {
            val favCurrencies = getFavoriteCurrenciesUseCase()
            actualCurrency = router.getLastResult<CurrencyDomainModel>() ?: actualCurrency
            val newCurrency = actualCurrency ?: getDefaultCurrencyUseCase()

            val exchangeList = getExchangeRatesUseCase(
                originCurrencyCode = newCurrency.code.orEmpty(),
                sum = state.sum.replace(" ", "").toDoubleOrNull() ?: 0.0,
                currencies = favCurrencies.map { it.code }
            )
            ConverterScreenIntent.Update(
                state.sum,
                newCurrency,
                exchangeList
            )
        }
        is ConverterScreenIntent.Update -> null
    }
}