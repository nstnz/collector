package com.nstnz.collector.common.feature.currencies.data.network.datasource

import com.nstnz.collector.common.basic.data.network.NetworkResponse
import com.nstnz.collector.common.basic.data.network.retrieve
import com.nstnz.collector.common.feature.currencies.data.network.model.CommonCurrencyModelDto
import com.nstnz.collector.common.feature.currencies.data.network.model.CryptoCurrencyModelDto
import com.nstnz.collector.common.feature.currencies.data.network.model.CurrencyModelDto
import com.nstnz.collector.common.feature.currencies.data.network.model.RateModelDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class CurrenciesNetworkDataSource(
    private val httpClient: HttpClient,
    private val json: Json,
) {

    suspend fun getSupportedCurrencies(): List<CurrencyModelDto> {
        val commonUri = URLBuilder("https://api.exchangerate.host/symbols").buildString()
        val cryptoUri = URLBuilder("https://api.exchangerate.host/cryptocurrencies").buildString()
        val commonResponse = httpClient.get(commonUri).retrieve()
        val cryptoResponse = httpClient.get(cryptoUri).retrieve()

        val result = mutableListOf<CurrencyModelDto>()
        if (commonResponse is NetworkResponse.Success) {
            val values =
                json.decodeFromString<Map<String, CommonCurrencyModelDto>>(commonResponse.obj["symbols"].toString())
            result.addAll(values.values)
        }
        if (cryptoResponse is NetworkResponse.Success) {
            val values =
                json.decodeFromString<Map<String, CryptoCurrencyModelDto>>(cryptoResponse.obj["cryptocurrencies"].toString())
            result.addAll(values.values)
        }

        return result
    }

    suspend fun getRatesForSum(
        originCurrency: String,
        sum: Float,
        currencies: List<String>
    ): List<RateModelDto> {
        val uri = URLBuilder("https://api.exchangerate.host/latest").apply {
            parameters.append("base", originCurrency)
            parameters.append("amount", sum.toString())
            parameters.append("symbols", currencies.joinToString(","))
        }.buildString()
        val response = httpClient.get(uri).retrieve()
        val result = mutableListOf<RateModelDto>()
        if (response is NetworkResponse.Success) {
            val values =
                json.decodeFromString<Map<String, Float>>(response.obj["rates"].toString())
            result.addAll(values.map {
                RateModelDto(it.key, it.value)
            })
        }
        return result
    }
}