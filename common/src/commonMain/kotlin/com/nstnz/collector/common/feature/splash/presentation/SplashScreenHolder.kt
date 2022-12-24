package com.nstnz.collector.common.feature.splash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import org.kodein.di.instance

@Composable
internal fun SplashScreenHolder() {
    val viewModel: SplashScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    SplashScreen(
        viewState = viewState,
    )
}
