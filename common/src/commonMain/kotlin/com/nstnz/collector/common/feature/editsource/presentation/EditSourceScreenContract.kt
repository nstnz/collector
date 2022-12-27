package com.nstnz.collector.common.feature.editsource.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel

internal sealed interface EditSourceScreenState : State {
    object Loading : EditSourceScreenState
    data class Default(
        val name: String,
        val currency: CurrencyDomainModel
    ) : EditSourceScreenState
}

internal sealed interface EditSourceScreenIntent : Intent {
    object GoBack : EditSourceScreenIntent
    object Load : EditSourceScreenIntent
    object OnResume : EditSourceScreenIntent
    data class ChangeCurrency(val currency: CurrencyDomainModel) : EditSourceScreenIntent
    data class ChangeName(val name: String) : EditSourceScreenIntent
    data class Update(
        val name: String,
        val currency: CurrencyDomainModel
    ) : EditSourceScreenIntent

    object SaveSource : EditSourceScreenIntent
}

internal sealed class EditSourceScreenSingleEvent : SingleEvent