package com.nstnz.collector.common.feature.welcome.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.core.di.splashScope
import com.nstnz.collector.common.feature.core.di.welcomeScope
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun WelcomeScreenHolder() {
    val viewModel: WelcomeScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent(welcomeScope) { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(WelcomeScreenIntent.Load)
            else -> Unit
        }
    }

    WelcomeScreen(
        viewState = viewState,
        onOkClick = { viewModel.sendIntent(WelcomeScreenIntent.GoForward) },
        onCurrencyClick = { viewModel.sendIntent(WelcomeScreenIntent.ClickCurrency(it)) },
        onSearch = { viewModel.sendIntent(WelcomeScreenIntent.Search(it)) },
    )
}
