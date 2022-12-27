package com.nstnz.collector.common.previews

import com.nstnz.collector.common.feature.core.domain.model.*

internal fun getMockCurrencyDomainModel(code: String = "USD") = CurrencyDomainModel(
    code, "", false, true
)

internal fun getMockSourceListDomainModel() = SourcesListDomainModel(
    sources = listOf(
        SourceDomainModel(
            id = "1",
            name = "Bank 1",
            originalCurrency = getMockCurrencyDomainModel(),
            counts = listOf(
                SourceCountDomainModel(
                    id = "1",
                    sourceId = "1",
                    isDefault = false,
                    originalSum = CurrencySumDomainModel(
                        sum = 100.0,
                        currency = getMockCurrencyDomainModel("AED")
                    ),
                    favoriteSums = listOf(
                        CurrencySumDomainModel(
                            sum = 123.0,
                            currency = getMockCurrencyDomainModel("USD")
                        ),
                        CurrencySumDomainModel(
                            sum = 4324.0,
                            currency = getMockCurrencyDomainModel("EUR")
                        ),
                    ),
                    selectedSums = listOf()
                ),
            ),
            favoriteCurrencies = listOf(getMockCurrencyDomainModel()),
            selectedCurrencies = listOf(),
        )
    ),
    favoriteCurrencies = listOf(getMockCurrencyDomainModel()),
    selectedCurrencies = listOf(),
)