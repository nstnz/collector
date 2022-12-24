package com.nstnz.collector.common.feature.source.domain.model

data class SourceFundModel(
    val id: String,
    val sourceId: String,
    val currencyCode: String,
    val sum: Float,
)
