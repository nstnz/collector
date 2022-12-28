package com.nstnz.collector.common.feature.currencies.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.nstnz.collector.common.basic.presentation.collectAsStateLifecycleAware
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.OnLifecycleEvent
import com.nstnz.collector.common.feature.core.di.converterScope
import com.nstnz.collector.common.feature.core.di.currenciesScope
import org.kodein.di.instance

@Composable
internal fun CurrenciesScreenHolder(
    multiCheck: Boolean,
    saveChanges: Boolean,
    currentCurrency: String?
) {
    val viewModel: CurrenciesScreenViewModel by SharedDI.di.instance(
        arg = CurrenciesViewModelParams(
            multiCheck,
            saveChanges,
            currentCurrency.orEmpty()
        )
    )
    val viewState by viewModel.viewState.collectAsStateLifecycleAware()

    OnLifecycleEvent(currenciesScope) { event ->
        when (event) {
            else -> Unit
        }
    }

    CurrenciesScreen(
        viewState = viewState,
        onCurrencyClick = { viewModel.sendIntent(CurrenciesScreenIntent.ClickCurrency(it)) },
        onSearch = { viewModel.sendIntent(CurrenciesScreenIntent.SearchCurrency(it)) },
        onBackCLick = { viewModel.sendIntent(CurrenciesScreenIntent.GoBack) },
        onSaveClick = { viewModel.sendIntent(CurrenciesScreenIntent.Save) },
    )
}
