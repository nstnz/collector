package com.nstnz.collector.common.feature.main.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourcesListScenario
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase
import com.nstnz.collector.common.feature.editsource.domain.usecase.DeleteSourceDataUseCase

internal class MainScreenViewModel(
    private val router: Router,
    private val getSourcesScenario: GetSourcesListScenario,
    private val deleteSourceDataUseCase: DeleteSourceDataUseCase
) : CoroutinesViewModel<MainScreenState, MainScreenIntent, MainScreenSingleEvent>() {

    override fun initialState(): MainScreenState = MainScreenState.Loading

    override fun reduce(intent: MainScreenIntent, prevState: MainScreenState): MainScreenState =
        when (intent) {
            is MainScreenIntent.Update -> MainScreenState.Default(intent.sourcesMainModel)
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
        is MainScreenIntent.Update -> null
        is MainScreenIntent.ShowAddCount -> {
            router.navigateToAddCountScreen(null)
            null
        }
        MainScreenIntent.ShowAddSource -> {
            router.navigateToAddSourceScreen()
            null
        }
        MainScreenIntent.OnResume -> {
            val sourcesModel = getSourcesScenario(null)
            MainScreenIntent.Update(sourcesModel)
        }
        is MainScreenIntent.DeleteSource -> {
            deleteSourceDataUseCase(intent.sourceId)
            MainScreenIntent.OnResume
        }
    }
}