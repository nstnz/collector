package com.nstnz.collector.common.feature.core.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CryptoCurrencyModelDto(
    val symbol: String?,
    override val name: String?,
    override val crypto: Boolean? = true
) : CurrencyModelDto {

    override val code: String?
        get() = symbol
}

@Serializable
internal data class CommonCurrencyModelDto(
    override val crypto: Boolean = false,
    val description: String?,
    override val code: String?,
) : CurrencyModelDto {

    override val name: String?
        get() = description

}

internal interface CurrencyModelDto {
    val crypto: Boolean?
    val name: String?
    val code: String?
}