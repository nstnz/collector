package com.nstnz.collector.common.feature.settings.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State

internal object SettingsScreenState : State

internal sealed interface SettingsScreenIntent : Intent {
    object ShowConverter : SettingsScreenIntent
    object ShowMainScreen : SettingsScreenIntent
}

internal sealed class SettingsScreenSingleEvent : SingleEvent