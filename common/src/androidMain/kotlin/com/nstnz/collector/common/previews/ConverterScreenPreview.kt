package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.converter.presentation.ConverterScreen
import com.nstnz.collector.common.feature.converter.presentation.ConverterScreenState
import com.nstnz.collector.common.feature.core.domain.model.CurrencySumDomainModel

@Composable
@Preview
private fun ConverterScreenPreview() {
    AppTheme {
        ConverterScreen(
            viewState = ConverterScreenState(
                sum = "354",
                currency = getMockCurrencyDomainModel("USD"),
                exchangeList = listOf(
                    CurrencySumDomainModel(
                        getMockCurrencyDomainModel("AED"),
                        53453.99
                    ) ,
                    CurrencySumDomainModel(
                        getMockCurrencyDomainModel("AED"),
                        53453.99
                    ) ,
                    CurrencySumDomainModel(
                        getMockCurrencyDomainModel("AED"),
                        53453.99
                    ) ,
                    CurrencySumDomainModel(
                        getMockCurrencyDomainModel("AED"),
                        53453.99
                    ) ,
                )
            )
        )
    }
}