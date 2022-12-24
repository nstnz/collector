package com.nstnz.collector.common.feature.settings.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrenciesUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.RefreshCurrenciesUseCase

internal class SettingsScreenViewModel(
    private val router: Router,
) : CoroutinesViewModel<SettingsScreenState, SettingsScreenIntent, SettingsScreenSingleEvent>() {

    override fun initialState(): SettingsScreenState = SettingsScreenState

    override fun reduce(
        intent: SettingsScreenIntent,
        prevState: SettingsScreenState
    ): SettingsScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: SettingsScreenIntent,
        state: SettingsScreenState
    ): SettingsScreenIntent? = when (intent) {
        SettingsScreenIntent.ShowConverter -> {
            router.navigateToConverterScreen()
            null
        }
        SettingsScreenIntent.ShowMainScreen -> {
            router.navigateToMainScreen()
            null
        }
    }
}