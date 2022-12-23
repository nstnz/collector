package com.nstnz.collector.common.feature.core.data

import com.nstnz.collector.common.feature.core.data.network.datasource.NetworkResponse
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

internal suspend inline fun HttpResponse.retrieve(): NetworkResponse {
    val obj = Json.parseToJsonElement(this.bodyAsText())

    return when (this.status) {
        HttpStatusCode.OK -> NetworkResponse.Success(obj as JsonObject)
        else -> NetworkResponse.Failed
    }
}