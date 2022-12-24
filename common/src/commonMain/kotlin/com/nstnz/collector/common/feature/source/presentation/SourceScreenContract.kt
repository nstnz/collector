package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.main.presentation.MainScreenIntent

internal sealed interface SourceScreenState : State {
    val sourceName: String

    data class Loading(
        override val sourceName: String = ""
    ) : SourceScreenState

    data class Default(
        override val sourceName: String
    ) : SourceScreenState
}

internal sealed interface SourceScreenIntent : Intent {
    data class ShowAddCount(val sourceId: String) : SourceScreenIntent
}

internal sealed class SourceScreenSingleEvent : SingleEvent