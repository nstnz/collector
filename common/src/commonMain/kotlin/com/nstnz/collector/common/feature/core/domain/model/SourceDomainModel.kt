package com.nstnz.collector.common.feature.core.domain.model

data class SourceDomainModel(
    val id: String,
    val name: String,
    val originalCurrency: CurrencyDomainModel,
    val counts: List<SourceCountDomainModel>,
    val favoriteCurrencies: List<CurrencyDomainModel>,
    val selectedCurrencies: List<CurrencyDomainModel>,
) {
    val originalFormattedSum: String
        get() = originalSum.formattedSum

    val originalSum: CurrencySumDomainModel
        get() = CurrencySumDomainModel(
            currency = originalCurrency,
            sum = counts.sumOf { it.getSumInCurrency(originalCurrency.code) }
        )

    val originalSums: List<CurrencySumDomainModel>
        get() = counts.map {
            it.originalSum
        }.groupBy {
            it.currency
        }.map {
            CurrencySumDomainModel(
                it.key,
                it.value.sumOf { it.sum }
            )
        }

    val favoriteSums: List<CurrencySumDomainModel>
        get() = favoriteCurrencies.map { currency ->
            CurrencySumDomainModel(
                currency = currency,
                sum = counts.sumOf { it.getSumInCurrency(currency.code) }
            )
        }

    val selectedSums: List<CurrencySumDomainModel>
        get() = selectedCurrencies.map { currency ->
            CurrencySumDomainModel(
                currency = currency,
                sum = counts.sumOf { it.getSumInCurrency(currency.code) }
            )
        }

    fun getSumInCurrency(code: String): Double =
        when {
            favoriteSums.any { it.currency.code == code } ->
                favoriteSums.first { it.currency.code == code }.sum
            selectedSums.any { it.currency.code == code } ->
                selectedSums.first { it.currency.code == code }.sum
            else -> 0.0
        }
}