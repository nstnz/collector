package com.nstnz.collector.common.feature.main.domain.model

data class SourcesMainModel(
    val currency: String,
    val sources: List<SourceMainModel>
) {

    val sum: Float
        get() = sources.sumOf { it.sum }.toFloat()
}

data class SourceMainModel(
    val name: String,
    val id: String,
    val funds: List<SourceFundMainModel>
) {

    val sum: Double
        get() = funds.sumOf { it.sum.toDouble() }
}

data class SourceFundMainModel(
    val id: String,
    val sum: Float,
    val originalSum: Float,
    val originalCurrency: String,
)
