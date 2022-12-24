package com.nstnz.collector.common.feature.converter.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.di.SharedDI
import org.kodein.di.instance

@Composable
internal fun ConverterScreenHolder() {
    val viewModel: ConverterScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    ConverterScreen(
        viewState = viewState,
        onMainTabClick = { viewModel.sendIntent(ConverterScreenIntent.ShowMainScreen) }
    )
}