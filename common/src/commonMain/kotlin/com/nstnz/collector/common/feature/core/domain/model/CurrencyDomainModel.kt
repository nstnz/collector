package com.nstnz.collector.common.feature.core.domain.model

data class CurrencyDomainModel(
    val code: String,
    val name: String,
    val crypto: Boolean,
    val isFavourite: Boolean,
) {

    val codeToShow: String
        get() = code.take(3).uppercase()
}
