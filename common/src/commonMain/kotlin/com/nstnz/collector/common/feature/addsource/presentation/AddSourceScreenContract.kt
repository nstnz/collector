package com.nstnz.collector.common.feature.addsource.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity

internal sealed interface AddSourceScreenState : State {
    object Loading : AddSourceScreenState
    data class Default(
        val name: String,
        val currency: CurrencyDomainModel,
        val showNameError: Boolean = false
    ) : AddSourceScreenState
}

internal sealed interface AddSourceScreenIntent : Intent {
    object OnResume : AddSourceScreenIntent
    object GoBack : AddSourceScreenIntent
    object Load : AddSourceScreenIntent
    data class Update(
        val name: String,
        val currency: CurrencyDomainModel
    ) : AddSourceScreenIntent

    object SaveSource : AddSourceScreenIntent
    data class ChangeCurrency(val currency: CurrencyDomainModel) : AddSourceScreenIntent
    data class ChangeName(val name: String) : AddSourceScreenIntent
}

internal sealed class AddSourceScreenSingleEvent : SingleEvent