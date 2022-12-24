package com.nstnz.collector.common.feature.main.domain.model

data class SourcesMainModel(
    val currency: String,
    val sources: List<SourceMainModel>
) {

    val sum: Float
        get() = sources.sumOf { it.sum.toDouble() }.toFloat()
}

data class SourceMainModel(
    val id: String,
    val name: String,
    val funds: List<SourceFundMainModel>
) {

    val sum: Float
        get() = funds.sumOf { it.sum.toDouble() }.toFloat()
}

data class SourceFundMainModel(
    val id: String,
    val sum: Float,
    val originalSum: Float,
    val originalCurrency: String,
)
