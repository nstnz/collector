package com.nstnz.collector.common.feature.addsource.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import org.kodein.di.instance

@Composable
internal fun AddSourceScreenHolder() {
    val viewModel: AddSourceScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    AddSourceScreen(
        viewState
    )
}
