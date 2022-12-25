package com.nstnz.collector.common.feature.listsource.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.source.domain.model.SourceFundModel
import com.nstnz.collector.common.feature.source.domain.model.SourceModel

internal sealed interface ListSourceScreenState : State {
    object Loading : ListSourceScreenState
    data class Default(
        val sources: List<SourceModel>,
        val selectedSourceId: String,
        val selectedSourceFundId: String
    ) : ListSourceScreenState
}

internal sealed interface ListSourceScreenIntent : Intent {
    object GoBack : ListSourceScreenIntent
    object Load : ListSourceScreenIntent
    data class SelectSourceAndFund(
        val sourceModel: SourceModel,
        val sourceFundModel: SourceFundModel
    ) : ListSourceScreenIntent

    data class Update(val sources: List<SourceModel>) : ListSourceScreenIntent
}

internal sealed class ListSourceScreenSingleEvent : SingleEvent