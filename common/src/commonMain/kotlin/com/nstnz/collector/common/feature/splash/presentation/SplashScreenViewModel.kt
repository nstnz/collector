package com.nstnz.collector.common.feature.splash.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrenciesUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.RefreshCurrenciesUseCase

internal class SplashScreenViewModel(
    private val router: Router,
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val refreshCurrenciesUseCase: RefreshCurrenciesUseCase,
) : CoroutinesViewModel<SplashScreenState, SplashScreenIntent, SplashScreenSingleEvent>() {

    init {
        sendIntent(SplashScreenIntent.Load)
    }

    override fun initialState(): SplashScreenState = SplashScreenState

    override fun reduce(
        intent: SplashScreenIntent,
        prevState: SplashScreenState
    ): SplashScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: SplashScreenIntent,
        state: SplashScreenState
    ): SplashScreenIntent? = when (intent) {
        SplashScreenIntent.Load -> {
            if (getCurrenciesUseCase().isEmpty()) {
                refreshCurrenciesUseCase()
            }
            router.navigateToMainScreen()
            null
        }
    }
}