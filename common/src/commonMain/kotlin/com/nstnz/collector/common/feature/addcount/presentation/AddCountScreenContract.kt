package com.nstnz.collector.common.feature.addcount.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State

internal sealed interface AddCountScreenState : State {
    object Default: AddCountScreenState
}

internal sealed interface AddCountScreenIntent : Intent {
}

internal sealed class AddCountScreenSingleEvent : SingleEvent