package com.nstnz.collector.common.feature.currencies.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class RateModelDto(
    val code: String,
    val sum: Double,
)