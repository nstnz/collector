package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.currencies.presentation.CurrenciesScreen
import com.nstnz.collector.common.feature.currencies.presentation.CurrenciesScreenState
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreenState

@Composable
@Preview
private fun CurrenciesScreenPreview() {
    AppTheme {
        CurrenciesScreen(
            viewState = CurrenciesScreenState(
                list = emptyList(),
                filteredList = listOf(
                    CurrencyEntity("AED", "Arab Edt", false, false),
                    CurrencyEntity("RUB", "DdjkKAS Edt", false, true),
                    CurrencyEntity("USD", "JHskjd Edt", false, false),
                    CurrencyEntity("EUR", "Arab", false, true),
                    CurrencyEntity("BTC", "Kksdjja asd", true, false),
                ),
                checkedCurrencies = emptyList(),
                multiCheck = true
            )
        )
    }
}