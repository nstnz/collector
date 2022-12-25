package com.nstnz.collector.common.feature.addsource.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenIntent
import com.nstnz.collector.common.feature.main.presentation.MainScreenIntent
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun AddSourceScreenHolder() {
    val viewModel: AddSourceScreenViewModel by SharedDI.di.instance()
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(AddSourceScreenIntent.OnResume)
            else -> Unit
        }
    }

    AddSourceScreen(
        viewState,
        onBackClick = { viewModel.sendIntent(AddSourceScreenIntent.GoBack) },
        onSaveClick = { viewModel.sendIntent(AddSourceScreenIntent.SaveSource) },
        onChangeDefaultCurrency = { viewModel.sendIntent(AddSourceScreenIntent.ChangeCurrency(it)) },
        onChangeName = { viewModel.sendIntent(AddSourceScreenIntent.ChangeName(it)) },
    )
}
