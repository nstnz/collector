package com.nstnz.collector.common.feature.addcount.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenIntent
import org.kodein.di.instance

@Composable
internal fun AddCountScreenHolder(
    sourceId: String?
) {
    val viewModel: AddCountScreenViewModel by SharedDI.di.instance(arg = sourceId.orEmpty())
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    AddCountScreen(
        viewState,
        onBackClick = { viewModel.sendIntent(AddCountScreenIntent.GoBack) },
        onSaveClick = { },
    )
}
