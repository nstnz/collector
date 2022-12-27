package com.nstnz.collector.common.feature.editcount.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceCountDomainModel

internal sealed interface EditCountScreenState : State {
    object Loading : EditCountScreenState
    data class Default(
        val sourceModel: SourceCountDomainModel?,
        val currency: CurrencyDomainModel,
        val sum: String,
    ) : EditCountScreenState
}

internal sealed interface EditCountScreenIntent : Intent {
    object GoBack : EditCountScreenIntent
    object Load : EditCountScreenIntent
    object OnResume : EditCountScreenIntent
    data class ChangeSum(val sum: String) : EditCountScreenIntent
    data class Update(
        val sourceModel: SourceCountDomainModel?,
        val currency: CurrencyDomainModel,
        val sum: String,
    ) : EditCountScreenIntent

    object Save : EditCountScreenIntent
}

internal sealed class EditCountScreenSingleEvent : SingleEvent