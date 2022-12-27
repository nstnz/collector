package com.nstnz.collector.common.feature.source.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun SourceScreenHolder(
    sourceId: String
) {
    val viewModel: SourceScreenViewModel by SharedDI.di.instance(arg = sourceId)
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(SourceScreenIntent.OnResume)
            else -> Unit
        }
    }

    SourceScreen(
        viewState,
        onBackClick = { viewModel.sendIntent(SourceScreenIntent.GoBack) },
        onAddCountClick = { viewModel.sendIntent(SourceScreenIntent.AddCount) },
        onEditClick = { viewModel.sendIntent(SourceScreenIntent.EditSource) },
        onCountClick = { viewModel.sendIntent(SourceScreenIntent.ShowCount(it)) },
        onDeleteCountClick = { viewModel.sendIntent(SourceScreenIntent.DeleteCount(it)) },
    )
}
