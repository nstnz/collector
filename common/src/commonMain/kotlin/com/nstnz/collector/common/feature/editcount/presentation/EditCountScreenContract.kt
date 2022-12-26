package com.nstnz.collector.common.feature.editcount.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenIntent
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.source.data.db.model.SourceFundEntity
import com.nstnz.collector.common.feature.source.domain.model.SourceFundModel
import com.nstnz.collector.common.feature.source.domain.model.SourceModel

internal sealed interface EditCountScreenState : State {
    object Loading : EditCountScreenState
    data class Default(
        val sourceModel: SourceFundEntity?,
        val currency: CurrencyEntity,
        val sum: String,
    ) : EditCountScreenState
}

internal sealed interface EditCountScreenIntent : Intent {
    object GoBack : EditCountScreenIntent
    object Load : EditCountScreenIntent
    object OnResume : EditCountScreenIntent
    object Delete : EditCountScreenIntent
    object SelectCurrency : EditCountScreenIntent
    data class ChangeSum(val sum: String) : EditCountScreenIntent
    data class Update(
        val sourceModel: SourceFundEntity?,
        val currency: CurrencyEntity,
        val sum: String,
    ) : EditCountScreenIntent

    object Save : EditCountScreenIntent
}

internal sealed class EditCountScreenSingleEvent : SingleEvent