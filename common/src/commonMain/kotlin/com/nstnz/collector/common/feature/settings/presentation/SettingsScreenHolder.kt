package com.nstnz.collector.common.feature.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.feature.main.presentation.MainScreenIntent
import org.kodein.di.instance

@Composable
internal fun SettingsScreenHolder() {
    val viewModel: SettingsScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    SettingsScreen(
        viewState = viewState,
        onConverterTabCLick = { viewModel.sendIntent(SettingsScreenIntent.ShowConverter) },
        onMainTabClick = { viewModel.sendIntent(SettingsScreenIntent.ShowMainScreen) },
    )
}
