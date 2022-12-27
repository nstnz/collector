package com.nstnz.collector.common.feature.currencies.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity

internal data class CurrenciesScreenState(
    val list: List<CurrencyDomainModel>,
    val filteredList: List<CurrencyDomainModel>,
    val checkedCurrencies: List<CurrencyDomainModel>,
    val multiCheck: Boolean,
    val searchText: String = ""
) : State

internal sealed interface CurrenciesScreenIntent : Intent {
    object GoBack : CurrenciesScreenIntent
    object LoadCurrencies : CurrenciesScreenIntent
    data class ShowCurrencies(
        val list: List<CurrencyDomainModel>,
        val filteredList: List<CurrencyDomainModel>,
        val checkedCurrencies: List<CurrencyDomainModel>,
    ) : CurrenciesScreenIntent

    data class ClickCurrency(
        val currency: CurrencyDomainModel,
    ) : CurrenciesScreenIntent

    data class SearchCurrency(
        val searchString: String,
    ) : CurrenciesScreenIntent
}

internal sealed class CurrenciesScreenSingleEvent : SingleEvent