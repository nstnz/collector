package com.nstnz.collector.common.feature.splash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenIntent
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun SplashScreenHolder() {
    val viewModel: SplashScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(SplashScreenIntent.Load)
            else -> Unit
        }
    }

    SplashScreen(
        viewState = viewState,
    )
}
