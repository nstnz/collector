package com.nstnz.collector.common.feature.listsource.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.splash.presentation.SplashScreenIntent
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun ListSourceScreenHolder(sourceId: String?, sourceFundId: String?) {
    val viewModel: ListSourceScreenViewModel by SharedDI.di.instance(
        arg = ListSourceViewModelParams(
            sourceId.orEmpty(),
            sourceFundId.orEmpty()
        )
    )
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(ListSourceScreenIntent.Load)
            else -> Unit
        }
    }

    ListSourceScreen(
        viewState = viewState,
        onBackClick = { viewModel.sendIntent(ListSourceScreenIntent.GoBack) },
        onSelect = { source, fund ->
            viewModel.sendIntent(
                ListSourceScreenIntent.SelectSourceAndFund(
                    source,
                    fund
                )
            )
        }
    )
}
