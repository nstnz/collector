package com.nstnz.collector.common.feature.converter.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.core.di.converterScope
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun ConverterScreenHolder() {
    val viewModel: ConverterScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent(converterScope) { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(ConverterScreenIntent.OnResume)
            else -> Unit
        }
    }

    ConverterScreen(
        viewState = viewState,
        onMainTabClick = { viewModel.sendIntent(ConverterScreenIntent.ShowMainScreen) },
        onSettingsTabClick = { viewModel.sendIntent(ConverterScreenIntent.ShowSettingsScreen) },
        onChangeCurrencyClick = { viewModel.sendIntent(ConverterScreenIntent.ChangeCurrency) },
        onChangeSum = { viewModel.sendIntent(ConverterScreenIntent.ChangeSum(it)) },
    )
}
