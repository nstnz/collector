package com.nstnz.collector.common.feature.splash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenIntent
import com.nstnz.collector.common.feature.core.di.splashScope
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun SplashScreenHolder() {
    val viewModel: SplashScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent(splashScope) { event ->
        when (event) {
            else -> Unit
        }
    }

    SplashScreen(
        viewState = viewState,
        onReady = { viewModel.sendIntent(SplashScreenIntent.Load) }
    )
}
