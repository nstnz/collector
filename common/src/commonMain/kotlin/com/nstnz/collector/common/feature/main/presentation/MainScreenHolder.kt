package com.nstnz.collector.common.feature.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.source.presentation.SourceScreenIntent
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun MainScreenHolder() {
    val viewModel: MainScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(MainScreenIntent.Load)
            else -> Unit
        }
    }

    MainScreen(
        viewState = viewState,
        onSourceClick = { viewModel.sendIntent(MainScreenIntent.ShowSource(it)) },
        onConverterTabCLick = { viewModel.sendIntent(MainScreenIntent.ShowConverter) },
        onSettingsTabClick = { viewModel.sendIntent(MainScreenIntent.ShowSettingsScreen) },
        onAddCount = { viewModel.sendIntent(MainScreenIntent.ShowAddCount) },
        onAddSource = { viewModel.sendIntent(MainScreenIntent.ShowAddSource) },
    )
}
