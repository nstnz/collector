package com.nstnz.collector.common.feature.core.domain.model

data class SourceCountDomainModel(
    val id: String,
    val sourceId: String,
    val isDefault: Boolean,
    val originalSum: CurrencySumDomainModel,
    val favoriteSums: List<CurrencySumDomainModel>,
    val selectedSums: List<CurrencySumDomainModel>,
) {
    val originalFormattedSum: String
        get() = originalSum.formattedSum

    fun getSumInCurrency(code: String): Double =
        when {
            originalSum.currency.code == code -> originalSum.sum
            favoriteSums.any { it.currency.code == code } ->
                favoriteSums.first { it.currency.code == code }.sum
            selectedSums.any { it.currency.code == code } ->
                selectedSums.first { it.currency.code == code }.sum
            else -> 0.0
        }
}
