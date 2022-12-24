package com.nstnz.collector.common.feature.converter.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State

internal object ConverterScreenState : State

internal sealed interface ConverterScreenIntent : Intent {
    object ShowSettingsScreen : ConverterScreenIntent
    object ShowMainScreen : ConverterScreenIntent
    object ChangeCurrencies : ConverterScreenIntent
}

internal sealed class ConverterScreenSingleEvent : SingleEvent