package com.nstnz.collector.common.feature.addcount.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.source.domain.model.SourceFundModel
import com.nstnz.collector.common.feature.source.domain.model.SourceModel

internal sealed interface AddCountScreenState : State {
    object Loading: AddCountScreenState
    data class Default(
        val sourceModel: SourceModel?,
        val currency: String,
        val sum: String,
        val fund: SourceFundModel?
    ) : AddCountScreenState
}

internal sealed interface AddCountScreenIntent : Intent {
    object Load : AddCountScreenIntent
    data class ChangeSum(val sum: String) : AddCountScreenIntent
    object GoBack : AddCountScreenIntent
    data class Update(
        val sourceModel: SourceModel?,
        val currency: String,
        val sum: String,
        val fund: SourceFundModel?
    ) : AddCountScreenIntent
    data class Save(
        val sourceModel: SourceModel,
        val currency: String,
        val sum: String,
        val fund: SourceFundModel
    ) : AddCountScreenIntent
}

internal sealed class AddCountScreenSingleEvent : SingleEvent