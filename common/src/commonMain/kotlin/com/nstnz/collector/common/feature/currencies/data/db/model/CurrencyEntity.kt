package com.nstnz.collector.common.feature.currencies.data.db.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CurrencyEntity(
    @SerialName("code")
    val code: String,
    @SerialName("name")
    val name: String,
    @SerialName("crypto")
    val crypto: Boolean,
    @SerialName("isFavourite")
    val isFavourite: Boolean,
)