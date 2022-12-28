package com.nstnz.collector.common.feature.welcome.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.usecase.GetDefaultCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.SaveDefaultCurrencyUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrenciesUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.RefreshCurrenciesUseCase

internal class WelcomeScreenViewModel(
    private val router: Router,
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val saveDefaultCurrencyUseCase: SaveDefaultCurrencyUseCase,
) : CoroutinesViewModel<WelcomeScreenState, WelcomeScreenIntent, WelcomeScreenSingleEvent>() {

    override fun initialState(): WelcomeScreenState = WelcomeScreenState()

    override fun reduce(
        intent: WelcomeScreenIntent,
        prevState: WelcomeScreenState
    ): WelcomeScreenState = when (intent) {
        is WelcomeScreenIntent.ClickCurrency -> prevState.copy(
            checkedCode = intent.currencyDomainModel.code
        )
        is WelcomeScreenIntent.Update -> prevState.copy(
            currencies = intent.currencies,
            filteredCurrencies = intent.filteredCurrencies
        )
        else -> prevState
    }


    override suspend fun performSideEffects(
        intent: WelcomeScreenIntent,
        state: WelcomeScreenState
    ): WelcomeScreenIntent? = when (intent) {
        WelcomeScreenIntent.Load -> {
            val currencies = getCurrenciesUseCase()
            WelcomeScreenIntent.Update(currencies, currencies)
        }
        is WelcomeScreenIntent.ClickCurrency -> null
        is WelcomeScreenIntent.Update -> null
        WelcomeScreenIntent.GoForward -> {
            if (!state.checkedCode.isNullOrEmpty()) {
                saveDefaultCurrencyUseCase(state.checkedCode)
                router.navigateToMainScreen()
            }
            null
        }
        is WelcomeScreenIntent.Search -> {
            val filteredList =
                state.currencies.filter {
                    it.code.contains(intent.str, ignoreCase = true) ||
                            it.name.contains(intent.str, ignoreCase = true)
                }.ifEmpty { state.currencies }
            WelcomeScreenIntent.Update(state.currencies, filteredList)
        }
    }
}