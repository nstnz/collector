package com.nstnz.collector.common.feature.splash.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State

internal object SplashScreenState : State

internal sealed interface SplashScreenIntent : Intent {
    object Load : SplashScreenIntent
}

internal sealed class SplashScreenSingleEvent : SingleEvent