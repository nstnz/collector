package com.nstnz.collector.common.feature.converter.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.model.CurrencySumDomainModel

internal data class ConverterScreenState(
    val sum: String = "",
    val currency: CurrencyDomainModel?,
    val exchange: CurrencySumDomainModel?
) : State

internal sealed interface ConverterScreenIntent : Intent {
    object ShowSettingsScreen : ConverterScreenIntent
    object ShowMainScreen : ConverterScreenIntent
    object ChangeFirstCurrency : ConverterScreenIntent
    object ChangeSecondCurrency : ConverterScreenIntent
    object OnResume : ConverterScreenIntent
    data class ChangeSum(val sum: String) : ConverterScreenIntent
    data class Update(
        val sum: String,
        val currency: CurrencyDomainModel?,
        val exchange: CurrencySumDomainModel?
    ) : ConverterScreenIntent
}

internal sealed class ConverterScreenSingleEvent : SingleEvent