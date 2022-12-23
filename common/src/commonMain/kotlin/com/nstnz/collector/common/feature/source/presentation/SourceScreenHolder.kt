package com.nstnz.collector.common.feature.source.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.di.SharedDI
import org.kodein.di.instance

@Composable
internal fun SourceScreenHolder(
    sourceId: String
) {
    val viewModel: SourceScreenViewModel by SharedDI.di.instance(arg = sourceId)
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    SourceScreen(
        viewState
    )
}
