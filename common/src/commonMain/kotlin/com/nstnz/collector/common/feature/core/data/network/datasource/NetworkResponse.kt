package com.nstnz.collector.common.feature.core.data.network.datasource

import kotlinx.serialization.json.JsonObject

internal sealed interface NetworkResponse {

    object Failed : NetworkResponse
    data class Success(val obj: JsonObject) : NetworkResponse
}