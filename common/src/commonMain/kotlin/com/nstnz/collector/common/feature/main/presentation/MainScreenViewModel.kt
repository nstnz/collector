package com.nstnz.collector.common.feature.main.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.auth.domain.UseCase2
import com.nstnz.collector.common.feature.core.domain.usecase.GetCurrenciesUseCase

internal class MainScreenViewModel(
    private val router: Router,
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : CoroutinesViewModel<MainScreenState, MainScreenIntent, MainScreenSingleEvent>() {

    override fun initialState(): MainScreenState = MainScreenState

    override fun reduce(intent: MainScreenIntent, prevState: MainScreenState): MainScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: MainScreenIntent,
        state: MainScreenState
    ): MainScreenIntent? = when (intent) {
        is MainScreenIntent.ShowSource -> {
            getCurrenciesUseCase.invoke()

           // router.navigateToSourceScreen(intent.sourceId)
            null
        }
        MainScreenIntent.ShowConverter -> {
            router.navigateToConverterScreen()
            null
        }
    }
}