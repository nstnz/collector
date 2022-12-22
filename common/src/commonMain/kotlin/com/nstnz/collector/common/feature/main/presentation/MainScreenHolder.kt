package com.nstnz.collector.common.feature.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.di.SharedDI
import org.kodein.di.instance

@Composable
internal fun MainScreenHolder() {
    val viewModel by SharedDI.di.instance<MainScreenViewModel>()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    MainScreen(
        viewState
    )
}
