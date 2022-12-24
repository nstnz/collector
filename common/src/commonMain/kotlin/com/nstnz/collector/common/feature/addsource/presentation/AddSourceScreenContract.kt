package com.nstnz.collector.common.feature.addsource.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State

internal sealed interface AddSourceScreenState : State {
    object Default: AddSourceScreenState
}

internal sealed interface AddSourceScreenIntent : Intent {
    object GoBack: AddSourceScreenIntent
    data class SaveSource(val name: String): AddSourceScreenIntent
}

internal sealed class AddSourceScreenSingleEvent : SingleEvent