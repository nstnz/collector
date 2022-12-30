package com.nstnz.collector.common.feature.core.domain.model

data class SourceDomainModel(
    val id: String,
    val name: String,
    val originalCurrency: CurrencyDomainModel,
    val counts: List<SourceCountDomainModel>,
    val favoriteCurrencies: List<CurrencyDomainModel>,
) {
    val originalFormattedSum: String
        get() = originalSum.formattedSum

    val originalSum: CurrencySumDomainModel
        get() = CurrencySumDomainModel(
            currency = originalCurrency,
            sum = counts.sumOf { it.getSumInCurrency(originalCurrency.code) }
        )

    val defaultSum: CurrencySumDomainModel
        get() = CurrencySumDomainModel(
            currency = allFavoriteSums.first { it.currency.isDefault }.currency,
            sum = counts.sumOf { it.getSumInCurrency(allFavoriteSums.first { it.currency.isDefault }.currency.code) }
        )

    private val allFavoriteSums: List<CurrencySumDomainModel>
        get() = favoriteCurrencies.map { currency ->
            CurrencySumDomainModel(
                currency = currency,
                sum = counts.sumOf { it.getSumInCurrency(currency.code) }
            )
        }

    val favoriteSums: List<CurrencySumDomainModel>
        get() = allFavoriteSums.filter {
            !it.currency.isDefault
        }

    fun getSumInCurrency(code: String): Double =
        when {
            allFavoriteSums.any { it.currency.code == code } ->
                allFavoriteSums.first { it.currency.code == code }.sum
            else -> 0.0
        }
}