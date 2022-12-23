package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State

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
}

internal sealed class SourceScreenSingleEvent : SingleEvent