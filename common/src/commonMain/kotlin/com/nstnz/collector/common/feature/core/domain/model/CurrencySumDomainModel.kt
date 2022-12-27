package com.nstnz.collector.common.feature.core.domain.model

import com.nstnz.collector.common.format

data class CurrencySumDomainModel(
    val currency: CurrencyDomainModel,
    val sum: Double
) {
    val formattedSum: String
        get() = if (currency.crypto) {
            "${sum} ${currency.code}"
        } else {
            "${format(sum)} ${currency.code}"
        }
}
