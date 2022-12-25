package com.nstnz.collector.common.feature.source.domain.model

data class SourceModel(
    val id: String,
    val name: String,
    val currencyCode: String,
    val funds: List<SourceFundModel>
)
