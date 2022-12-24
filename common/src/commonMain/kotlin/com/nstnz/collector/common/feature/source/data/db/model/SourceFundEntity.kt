package com.nstnz.collector.common.feature.source.data.db.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SourceFundEntity(
    @SerialName("id")
    val id: String,
    @SerialName("sourceId")
    val sourceId: String,
    @SerialName("currencyCode")
    val currencyCode: String,
    @SerialName("sum")
    val sum: Float,
    @SerialName("default")
    val default: Boolean,
)