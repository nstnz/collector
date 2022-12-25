package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.listsource.presentation.ListSourceScreen
import com.nstnz.collector.common.feature.listsource.presentation.ListSourceScreenState
import com.nstnz.collector.common.feature.main.domain.model.SourceFundMainModel
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.main.presentation.MainScreenState
import com.nstnz.collector.common.feature.source.domain.model.SourceFundModel
import com.nstnz.collector.common.feature.source.domain.model.SourceModel
import com.nstnz.collector.common.feature.splash.presentation.SplashScreen
import com.nstnz.collector.common.feature.splash.presentation.SplashScreenState

@Composable
@Preview
private fun ListSourceScreenPreview() {
    AppTheme {
        ListSourceScreen(
            ListSourceScreenState.Default(
                selectedSourceId = "2",
                sources = listOf(
                    SourceModel(
                        "1", "Tinkoff", "", funds = listOf(
                            SourceFundModel(
                                id = "",
                                name = "Ololo",
                                sum = 44.653f,
                                currencyCode = "Usd",
                                isDefault = true,
                                sourceId = ""
                            ),
                            SourceFundModel(
                                id = "",
                                name = "Ololo",
                                sum = 44.653f,
                                currencyCode = "Usd",
                                isDefault = true,
                                sourceId = ""
                            ),
                        )
                    ),
                    SourceModel(
                        "2", "Tinkoff","", funds = listOf(
                            SourceFundModel(
                                id = "",
                                name = "Ololo",
                                sum = 44.653f,
                                currencyCode = "Usd",
                                isDefault = true,
                                sourceId = ""
                            ),
                            SourceFundModel(
                                id = "32",
                                name = "Ololo",
                                sum = 44.653f,
                                currencyCode = "Usd",
                                isDefault = true,
                                sourceId = ""
                            ),
                        )
                    ),

                )
            )
        )
    }
}