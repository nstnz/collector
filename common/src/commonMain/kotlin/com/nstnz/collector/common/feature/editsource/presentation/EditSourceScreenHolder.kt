package com.nstnz.collector.common.feature.editsource.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun EditSourceScreenHolder(sourceId: String?) {
    val viewModel: EditSourceScreenViewModel by SharedDI.di.instance(
        arg = sourceId.orEmpty()
    )
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent(viewModel::class) { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(EditSourceScreenIntent.OnResume)
            else -> Unit
        }
    }

    EditSourceScreen(
        viewState,
        onBackClick = { viewModel.sendIntent(EditSourceScreenIntent.GoBack) },
        onSaveClick = { viewModel.sendIntent(EditSourceScreenIntent.SaveSource) },
        onChangeDefaultCurrency = { viewModel.sendIntent(EditSourceScreenIntent.ChangeCurrency(it)) },
        onChangeName = { viewModel.sendIntent(EditSourceScreenIntent.ChangeName(it)) },
    )
}
