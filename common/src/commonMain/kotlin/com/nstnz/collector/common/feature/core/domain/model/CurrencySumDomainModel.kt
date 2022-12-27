package com.nstnz.collector.common.feature.core.domain.model

data class CurrencySumDomainModel(
    val currency: CurrencyDomainModel,
    val sum: Double
) {
    val formattedSum: String
        get() = "${sum} ${currency.code}"
}
