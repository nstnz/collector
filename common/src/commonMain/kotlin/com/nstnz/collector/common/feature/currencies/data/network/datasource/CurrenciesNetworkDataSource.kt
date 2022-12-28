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
import io.ktor.util.date.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

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
        sum: Double,
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
                json.decodeFromString<Map<String, Double?>>(response.obj["rates"].toString())
            result.addAll(values.map {
                RateModelDto(it.key, it.value ?: 0.0)
            })
        }
        return result
    }

    @OptIn(ExperimentalTime::class)
    suspend fun getHistoricalRatesForSum(
        originCurrency: String,
        sum: Double,
        currency: String
    ): List<Double> {
        val end = GMTDate(getTimeMillis())
        val start = GMTDate(getTimeMillis()).minus(kotlin.time.Duration.days(12 * 30))
        val formatFun: (date: GMTDate) -> String = {
            val day = it.dayOfMonth.toString().let {
                if (it.length == 1) "0$it"
                else it
            }
            val month = (it.month.ordinal + 1).toString().let {
                if (it.length == 1) "0$it"
                else it
            }
            "${it.year}-$month-${day}"
        }

        val uri = URLBuilder("https://api.exchangerate.host/timeseries").apply {
            parameters.append("base", originCurrency)
            parameters.append("amount", sum.toString())
            parameters.append("symbols", currency)
            parameters.append("start_date", formatFun(start))
            parameters.append("end_date", formatFun(end))
        }.buildString()
        val response = httpClient.get(uri).retrieve()
        val result = mutableListOf<Double>()
        if (response is NetworkResponse.Success) {
            val values =
                json.decodeFromString<Map<String, JsonObject>>(response.obj["rates"].toString())
            result.addAll(values.map {
                it.value[currency].toString().toDoubleOrNull() ?: 0.0
            })

            val pointsCount = 70
            val count = result.size / pointsCount
            return result.filterIndexed { index, _ ->
                index % count == 0
                        || index == 0
                        || index == result.lastIndex
            }.takeLast(pointsCount)
        }
        return result
    }
}