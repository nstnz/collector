package com.nstnz.collector.common.feature.addcount.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceCountDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceDomainModel

internal sealed interface AddCountScreenState : State {
    object Loading : AddCountScreenState
    data class Default(
        val sourceModel: SourceDomainModel?,
        val currency: CurrencyDomainModel,
        val sum: String,
    ) : AddCountScreenState
}

internal sealed interface AddCountScreenIntent : Intent {
    object Load : AddCountScreenIntent
    object SelectCurrency : AddCountScreenIntent
    data class ChangeSum(val sum: String) : AddCountScreenIntent
    object GoBack : AddCountScreenIntent
    object OnResume : AddCountScreenIntent
    data class Update(
        val sourceModel: SourceDomainModel?,
        val currency: CurrencyDomainModel,
        val sum: String,
    ) : AddCountScreenIntent

    object Save : AddCountScreenIntent
}

internal sealed class AddCountScreenSingleEvent : SingleEvent