package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.main.domain.model.SourceMainModel
import com.nstnz.collector.common.feature.main.presentation.MainScreenIntent
import com.nstnz.collector.common.feature.source.domain.model.SourceModel

internal sealed interface SourceScreenState : State {
    object Loading : SourceScreenState

    data class Default(
        val sourceMainModel: SourceMainModel
    ) : SourceScreenState
}

internal sealed interface SourceScreenIntent : Intent {
    object Load : SourceScreenIntent
    object GoBack : SourceScreenIntent
    object EditSource : SourceScreenIntent
    object AddCount : SourceScreenIntent
    data class ShowAddCount(val sourceId: String) : SourceScreenIntent
    data class Update(val sourceMainModel: SourceMainModel) : SourceScreenIntent
}

internal sealed class SourceScreenSingleEvent : SingleEvent