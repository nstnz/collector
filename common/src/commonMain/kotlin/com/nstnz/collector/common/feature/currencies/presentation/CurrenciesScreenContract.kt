package com.nstnz.collector.common.feature.currencies.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity

internal data class CurrenciesScreenState(
    val list: List<CurrencyEntity>,
    val filteredList: List<CurrencyEntity>,
    val checkedCurrencies: List<CurrencyEntity>,
    val multiCheck: Boolean
) : State

internal sealed interface CurrenciesScreenIntent : Intent {
    object GoBack : CurrenciesScreenIntent
    object LoadCurrencies : CurrenciesScreenIntent
    data class ShowCurrencies(
        val list: List<CurrencyEntity>,
        val filteredList: List<CurrencyEntity>,
        val checkedCurrencies: List<CurrencyEntity>,
    ) : CurrenciesScreenIntent

    data class ClickCurrency(
        val currency: CurrencyEntity,
    ) : CurrenciesScreenIntent

    data class SearchCurrency(
        val searchString: String,
    ) : CurrenciesScreenIntent
}

internal sealed class CurrenciesScreenSingleEvent : SingleEvent