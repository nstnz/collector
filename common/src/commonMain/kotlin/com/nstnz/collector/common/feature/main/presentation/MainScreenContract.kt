package com.nstnz.collector.common.feature.main.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State

internal object MainScreenState : State

internal sealed interface MainScreenIntent : Intent {
}

internal sealed class MainScreenSingleEvent : SingleEvent