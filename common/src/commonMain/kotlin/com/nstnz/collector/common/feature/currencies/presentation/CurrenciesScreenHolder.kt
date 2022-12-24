package com.nstnz.collector.common.feature.currencies.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import org.kodein.di.instance

@Composable
internal fun CurrenciesScreenHolder(multiCheck: Boolean, saveChanges: Boolean) {
    val viewModel: CurrenciesScreenViewModel by SharedDI.di.instance(
        arg = CurrenciesViewModelParams(
            multiCheck,
            saveChanges
        )
    )
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    CurrenciesScreen(
        viewState = viewState,
        onCurrencyClick = { viewModel.sendIntent(CurrenciesScreenIntent.ClickCurrency(it)) },
        onSearch = { viewModel.sendIntent(CurrenciesScreenIntent.SearchCurrency(it)) },
        onBackCLick = { viewModel.sendIntent(CurrenciesScreenIntent.GoBack) }
    )
}
