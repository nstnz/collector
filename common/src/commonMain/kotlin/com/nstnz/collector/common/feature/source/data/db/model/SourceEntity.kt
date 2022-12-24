package com.nstnz.collector.common.feature.source.data.db.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SourceEntity(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
)