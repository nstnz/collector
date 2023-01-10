package com.nstnz.collector.common.feature.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.core.di.converterScope
import com.nstnz.collector.common.feature.core.di.settingsScope
import com.nstnz.collector.common.feature.main.presentation.MainScreenIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun SettingsScreenHolder() {
    val viewModel: SettingsScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent(settingsScope) { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(SettingsScreenIntent.OnResume)
            else -> Unit
        }
    }

    SettingsScreen(
        viewState = viewState,
        onConverterTabCLick = { viewModel.sendIntent(SettingsScreenIntent.ShowConverter) },
        onMainTabClick = { viewModel.sendIntent(SettingsScreenIntent.ShowMainScreen) },
        onChangeCurrencyClick = { viewModel.sendIntent(SettingsScreenIntent.ChangeCurrency) },
        onLoginClick = { viewModel.sendIntent(SettingsScreenIntent.Login) },
        onLogoutClick = { viewModel.sendIntent(SettingsScreenIntent.Logout) },
    )
}