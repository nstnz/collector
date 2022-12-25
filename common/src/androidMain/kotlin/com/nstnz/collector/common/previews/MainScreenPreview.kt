package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.main.domain.model.SourceFundMainModel
import com.nstnz.collector.common.feature.main.domain.model.SourceMainModel
import com.nstnz.collector.common.feature.main.domain.model.SourcesMainModel
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.main.presentation.MainScreenState

@Composable
@Preview
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            MainScreenState.Default(
                SourcesMainModel(
                    currency = CurrencyEntity("USD", "", false, false),
                    sources = listOf(
                        SourceMainModel(
                            name = "Tinkoff",
                            id = "",
                            funds = listOf(
                                SourceFundMainModel(
                                    id = "",
                                    originalCurrency = "RUB",
                                    originalSum = 123f,
                                    sum = 44.653f
                                ),
                                SourceFundMainModel(
                                    id = "",
                                    originalCurrency = "BTC",
                                    originalSum = 123f,
                                    sum = 0.6583453f
                                ),
                            )
                        ),
                        SourceMainModel(
                            name = "Exmo",
                            id = "",
                            funds = listOf(
                                SourceFundMainModel(
                                    id = "",
                                    originalCurrency = "USD",
                                    originalSum = 123f,
                                    sum = 2627723f
                                ),
                            )
                        ),
                    )
                )
            )
        )
    }
}