package com.nstnz.collector.common.feature.core.domain.model

data class SourcesListDomainModel(
    val sources: List<SourceDomainModel>,
    val favoriteCurrencies: List<CurrencyDomainModel>,
) {
    val originalFormattedSum: String
        get() = favoriteSums.first().formattedSum

    val favoriteSums: List<CurrencySumDomainModel>
        get() = favoriteCurrencies.map { currency ->
            CurrencySumDomainModel(
                currency = currency,
                sum = sources.sumOf { it.getSumInCurrency(currency.code) }
            )
        }

    val originalSums: List<CurrencySumDomainModel>
        get() {
            val result = mutableMapOf<CurrencyDomainModel, Double>()
            sources.forEach {
                it.originalSums.forEach {
                    if (result.containsKey(it.currency)) {
                        result[it.currency] = result[it.currency]?.plus(it.sum) ?: 0.0
                    } else {
                        result[it.currency] = it.sum
                    }
                }
            }

            return result.map {
                CurrencySumDomainModel(
                    it.key,
                    it.value
                )
            }
        }
}