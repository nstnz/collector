package com.nstnz.collector.common.feature.main.domain.model

import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity

data class SourcesMainModel(
    val currency: CurrencyEntity,
    val sources: List<SourceMainModel>
) {

    val sum: Float
        get() = sources.sumOf { it.sum.toDouble() }.toFloat()

    val formattedSum: String
        get() = "${sum} ${currency.code}"
}

data class SourceMainModel(
    val id: String,
    val name: String,
    val defaultCurrency: CurrencyEntity,
    val funds: List<SourceFundMainModel>
) {

    val formattedSum: String
        get() = "${sum} ${defaultCurrency.code}"

    val sum: Float
        get() = funds.sumOf { it.sum.toDouble() }.toFloat()
}

data class SourceFundMainModel(
    val id: String,
    val sum: Float,
    val defaultCurrency: CurrencyEntity,
    val originalSum: Float,
    val originalCurrency: String,
) {

    val formattedSum: String
        get() = "${sum} ${defaultCurrency.code}"

    val formattedOriginalSum: String
        get() = "${originalSum} ${originalCurrency}"
}
