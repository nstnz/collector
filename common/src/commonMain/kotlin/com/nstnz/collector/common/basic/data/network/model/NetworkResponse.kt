package com.nstnz.collector.common.basic.data.network.model

import kotlinx.serialization.json.JsonObject

internal sealed interface NetworkResponse {

    object Failed : NetworkResponse
    data class Success(val obj: JsonObject) : NetworkResponse
}