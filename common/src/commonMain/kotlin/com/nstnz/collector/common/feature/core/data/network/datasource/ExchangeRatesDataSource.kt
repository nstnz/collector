package com.nstnz.collector.common.feature.core.data.network.datasource

import com.nstnz.collector.common.feature.core.data.network.model.CommonCurrencyModelDto
import com.nstnz.collector.common.feature.core.data.network.model.CryptoCurrencyModelDto
import com.nstnz.collector.common.feature.core.data.network.model.CurrencyModelDto
import com.nstnz.collector.common.feature.core.data.retrieve
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class ExchangeRatesDataSource(
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
            val values = json.decodeFromString<Map<String, CommonCurrencyModelDto>>(commonResponse.obj["symbols"].toString())
            result.addAll(values.values)
        }
        if (cryptoResponse is NetworkResponse.Success) {
            val values = json.decodeFromString<Map<String, CryptoCurrencyModelDto>>(cryptoResponse.obj["cryptocurrencies"].toString())
            result.addAll(values.values)
        }

        return result
    }

    suspend fun getRatesForSum(originCurrency: String, sum: Float, currencies: List<String>) {
        val uri = URLBuilder("https://api.exchangerate.host/latest").apply {
            parameters.append("base", originCurrency)
            parameters.append("amount", sum.toString())
            parameters.append("symbols", currencies.joinToString(","))
        }.buildString()
        httpClient.get(uri) {
            val a = this.body
            val b = 0
        }
    }
}