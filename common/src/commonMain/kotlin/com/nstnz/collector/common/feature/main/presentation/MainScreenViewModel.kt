package com.nstnz.collector.common.feature.main.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetMainCurrencyUseCase
import com.nstnz.collector.common.feature.main.domain.scenario.GetSourcesScenario
import com.nstnz.collector.common.feature.main.domain.usecase.GetSourcesDataUseCase

internal class MainScreenViewModel(
    private val router: Router,
    private val getSourcesScenario: GetSourcesScenario,
) : CoroutinesViewModel<MainScreenState, MainScreenIntent, MainScreenSingleEvent>() {

    init {
        sendIntent(MainScreenIntent.Load)
    }

    override fun initialState(): MainScreenState = MainScreenState.Loading

    override fun reduce(intent: MainScreenIntent, prevState: MainScreenState): MainScreenState =
        when (intent) {
            is MainScreenIntent.Update -> MainScreenState.Default(intent.sourcesMainModel,)
            else -> prevState
        }

    override suspend fun performSideEffects(
        intent: MainScreenIntent,
        state: MainScreenState
    ): MainScreenIntent? = when (intent) {
        is MainScreenIntent.ShowSource -> {
            router.navigateToSourceScreen(intent.sourceId)
            null
        }
        MainScreenIntent.ShowConverter -> {
            router.navigateToConverterScreen()
            null
        }
        MainScreenIntent.ShowSettingsScreen -> {
            router.navigateToSettingsScreen()
            null
        }
        MainScreenIntent.Load -> {
            val sourcesModel = getSourcesScenario()
            MainScreenIntent.Update(sourcesModel)
        }
        is MainScreenIntent.Update -> null
        is MainScreenIntent.ShowAddCount -> {
            router.navigateToAddCountScreen(null)
            null
        }
    }
}