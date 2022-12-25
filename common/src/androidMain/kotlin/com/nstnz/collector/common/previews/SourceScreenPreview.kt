package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.main.domain.model.SourceFundMainModel
import com.nstnz.collector.common.feature.main.domain.model.SourceMainModel
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreenState

@Composable
@Preview
private fun SourceScreenPreview() {
    val currencyEntity = CurrencyEntity("USD", "", false, false)
    AppTheme {
        SourceScreen(
            viewState = SourceScreenState.Default(
                SourceMainModel(
                    name = "Tinkoff",
                    id = "",
                    defaultCurrency = currencyEntity,
                    funds = listOf(
                        SourceFundMainModel(
                            id = "",
                            originalCurrency = "RUB",
                            originalSum = 123f,
                            sum = 44.653f,
                            defaultCurrency = currencyEntity,
                        ),
                        SourceFundMainModel(
                            id = "",
                            originalCurrency = "BTC",
                            originalSum = 123f,
                            sum = 0.6583453f,
                            defaultCurrency = currencyEntity,
                        ),
                    )
                )
            )
        )
    }
}