package com.nstnz.collector.common.feature.converter.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenIntent
import com.nstnz.collector.common.feature.core.di.addSourceScope
import com.nstnz.collector.common.feature.core.di.converterScope
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun ConverterScreenHolder() {
    val viewModel: ConverterScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent(converterScope) { event ->
        when (event) {
            else -> Unit
        }
    }

    ConverterScreen(
        viewState = viewState,
        onMainTabClick = { viewModel.sendIntent(ConverterScreenIntent.ShowMainScreen) },
        onSettingsTabClick = { viewModel.sendIntent(ConverterScreenIntent.ShowSettingsScreen) },
        onChangeCurrenciesClick = { viewModel.sendIntent(ConverterScreenIntent.ChangeCurrencies) },
    )
}
