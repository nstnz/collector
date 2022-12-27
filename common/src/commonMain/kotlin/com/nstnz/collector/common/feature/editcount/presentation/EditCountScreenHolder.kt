package com.nstnz.collector.common.feature.editcount.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun EditCountScreenHolder(sourceFundId: String?) {
    val viewModel: EditCountScreenViewModel by SharedDI.di.instance(
        arg = sourceFundId.orEmpty()
    )
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(EditCountScreenIntent.OnResume)
            else -> Unit
        }
    }

    EditCountScreen(
        viewState,
        onBackClick = { viewModel.sendIntent(EditCountScreenIntent.GoBack) },
        onSaveClick = { viewModel.sendIntent(EditCountScreenIntent.Save) },
        onChangeSum = { viewModel.sendIntent(EditCountScreenIntent.ChangeSum(it)) },
    )
}
