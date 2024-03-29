package com.nstnz.collector.common.feature.settings.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetDefaultCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.SaveDefaultCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.auth.GetAccountModelUseCase
import com.nstnz.collector.common.launchGoogleSignIn
import com.nstnz.collector.common.logout

internal class SettingsScreenViewModel(
    private val router: Router,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase,
    private val saveDefaultCurrencyUseCase: SaveDefaultCurrencyUseCase,
    private val getGoogleAuthTokenUseCase: GetAccountModelUseCase
) : CoroutinesViewModel<SettingsScreenState, SettingsScreenIntent, SettingsScreenSingleEvent>() {

    override fun initialState(): SettingsScreenState = SettingsScreenState(
        currency = null, favoriteCurrencies = emptyList(),
        account = getGoogleAuthTokenUseCase().takeIf { !it.googleAuthToken.isNullOrEmpty() }
    )

    override fun reduce(
        intent: SettingsScreenIntent,
        prevState: SettingsScreenState
    ): SettingsScreenState = when (intent) {
        is SettingsScreenIntent.Update -> prevState.copy(
            currency = intent.currency,
            favoriteCurrencies = intent.favoriteCurrencies,
            account = getGoogleAuthTokenUseCase().takeIf { !it.googleAuthToken.isNullOrEmpty() }
        )
        else -> prevState
    }

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
        SettingsScreenIntent.OnResume -> {
            val currency = router.getLastResult<CurrencyDomainModel>()
            if (currency != null) {
                saveDefaultCurrencyUseCase(currency)
            }
            val defaultCurrency = currency ?: getDefaultCurrencyUseCase()
            SettingsScreenIntent.Update(
                defaultCurrency,
                getFavoriteCurrenciesUseCase().filter { !it.isDefault })
        }
        is SettingsScreenIntent.Update -> null
        SettingsScreenIntent.ChangeCurrency -> {
            router.navigateToCurrenciesScreen(
                multiCheck = false,
                saveChanges = false,
                currency = state.currency?.code
            )
            null
        }
        SettingsScreenIntent.Login -> {
            launchGoogleSignIn()
            null
        }
        SettingsScreenIntent.Logout -> {
            logout()
            SettingsScreenIntent.Update(state.currency, state.favoriteCurrencies)
        }
    }
}