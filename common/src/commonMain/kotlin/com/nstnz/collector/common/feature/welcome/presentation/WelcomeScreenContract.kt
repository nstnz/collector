package com.nstnz.collector.common.feature.welcome.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel

internal data class WelcomeScreenState(
    val checkedCode: String? = null,
    val searchText: String? = null,
    val currencies: List<CurrencyDomainModel> = emptyList(),
    val filteredCurrencies: List<CurrencyDomainModel> = emptyList(),
) : State

internal sealed interface WelcomeScreenIntent : Intent {
    object Load : WelcomeScreenIntent
    data class Update(
        val currencies: List<CurrencyDomainModel>,
        val filteredCurrencies: List<CurrencyDomainModel> = emptyList(),
    ) : WelcomeScreenIntent

    object GoForward : WelcomeScreenIntent
    data class ClickCurrency(val currencyDomainModel: CurrencyDomainModel) : WelcomeScreenIntent
    data class Search(val str: String) : WelcomeScreenIntent
}

internal sealed class WelcomeScreenSingleEvent : SingleEvent