package com.nstnz.collector.common.feature.settings.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.AccountModel
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel

internal data class SettingsScreenState(
    val currency: CurrencyDomainModel?,
    val favoriteCurrencies: List<CurrencyDomainModel>,
    val account: AccountModel? = null
) : State

internal sealed interface SettingsScreenIntent : Intent {
    object ShowConverter : SettingsScreenIntent
    object ShowMainScreen : SettingsScreenIntent
    object ChangeCurrency : SettingsScreenIntent
    object Login : SettingsScreenIntent
    object Logout : SettingsScreenIntent
    object OnResume : SettingsScreenIntent
    data class Update(
        val currency: CurrencyDomainModel?,
        val favoriteCurrencies: List<CurrencyDomainModel>
    ) : SettingsScreenIntent
}

internal sealed class SettingsScreenSingleEvent : SingleEvent