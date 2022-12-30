package com.nstnz.collector.common.feature.core.domain.model

data class SourcesListDomainModel(
    val sources: List<SourceDomainModel>,
    val favoriteCurrencies: List<CurrencyDomainModel>,
) {
    val originalFormattedSum: String
        get() = allFavoriteSums.first { it.currency.isDefault }.formattedSum

    private val allFavoriteSums: List<CurrencySumDomainModel>
        get() = favoriteCurrencies.map { currency ->
            CurrencySumDomainModel(
                currency = currency,
                sum = sources.sumOf { it.getSumInCurrency(currency.code) }
            )
        }

    val favoriteSums: List<CurrencySumDomainModel>
        get() = allFavoriteSums.filter { !it.currency.isDefault }
}