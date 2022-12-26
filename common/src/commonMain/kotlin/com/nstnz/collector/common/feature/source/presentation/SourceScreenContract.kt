package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.main.domain.model.SourceMainModel

internal sealed interface SourceScreenState : State {
    object Loading : SourceScreenState

    data class Default(
        val sourceMainModel: SourceMainModel
    ) : SourceScreenState
}

internal sealed interface SourceScreenIntent : Intent {
    object OnResume : SourceScreenIntent
    object GoBack : SourceScreenIntent
    object EditSource : SourceScreenIntent
    object ChangeShownCurrency : SourceScreenIntent
    data class ShowCount(val sourceFundId: String) : SourceScreenIntent
    object AddCount : SourceScreenIntent
    data class ShowAddCount(val sourceId: String) : SourceScreenIntent
    data class Update(val sourceMainModel: SourceMainModel) : SourceScreenIntent
}

internal sealed class SourceScreenSingleEvent : SingleEvent