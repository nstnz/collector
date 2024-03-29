package com.nstnz.collector.common.feature.addcount.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenIntent
import com.nstnz.collector.common.feature.core.di.addCountScope
import moe.tlaster.precompose.lifecycle.Lifecycle
import org.kodein.di.instance

@Composable
internal fun AddCountScreenHolder(
    sourceId: String?,
    sourceFundId: String?,
) {
    val viewModel: AddCountScreenViewModel by SharedDI.di.instance(
        arg = AddCountViewModelParams(
            sourceId.orEmpty(), sourceFundId.orEmpty()
        )
    )
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent(addCountScope) { event ->
        when (event) {
            Lifecycle.State.Active -> viewModel.sendIntent(AddCountScreenIntent.OnResume)
            else -> Unit
        }
    }

    AddCountScreen(
        viewState,
        onBackClick = { viewModel.sendIntent(AddCountScreenIntent.GoBack) },
        onSaveClick = { viewModel.sendIntent(AddCountScreenIntent.Save) },
        onSelectCurrencyClick = { viewModel.sendIntent(AddCountScreenIntent.SelectCurrency) },
        onChangeSum = { viewModel.sendIntent(AddCountScreenIntent.ChangeSum(it)) },
    )
}
