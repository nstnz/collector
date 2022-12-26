package com.nstnz.collector.common.feature.converter.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.usecase.GetExchangeRatesUseCase

internal class ConverterScreenViewModel(
    private val router: Router,
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
) : CoroutinesViewModel<ConverterScreenState, ConverterScreenIntent, ConverterScreenSingleEvent>() {

    override fun initialState(): ConverterScreenState = ConverterScreenState

    override fun reduce(
        intent: ConverterScreenIntent,
        prevState: ConverterScreenState
    ): ConverterScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: ConverterScreenIntent,
        state: ConverterScreenState
    ): ConverterScreenIntent? = when (intent) {
        ConverterScreenIntent.ShowMainScreen -> {
            router.navigateToMainScreen()
            null
        }
        ConverterScreenIntent.ChangeCurrencies -> {
            router.navigateToCurrenciesScreen(multiCheck = true, saveChanges = false, currency = null)
            null
        }
        ConverterScreenIntent.ShowSettingsScreen -> {
            router.navigateToSettingsScreen()
            null
        }
    }
}