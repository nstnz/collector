package com.nstnz.collector.common.feature.editsource.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenIntent
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.source.domain.model.SourceFundModel
import com.nstnz.collector.common.feature.source.domain.model.SourceModel

internal sealed interface EditSourceScreenState : State {
    object Loading : EditSourceScreenState
    data class Default(
        val name: String,
        val currency: CurrencyEntity
    ) : EditSourceScreenState
}

internal sealed interface EditSourceScreenIntent : Intent {
    object GoBack : EditSourceScreenIntent
    object Load : EditSourceScreenIntent
    object OnResume : EditSourceScreenIntent
    object Delete : EditSourceScreenIntent
    data class ChangeCurrency(val currency: CurrencyEntity) : EditSourceScreenIntent
    data class ChangeName(val name: String) : EditSourceScreenIntent
    data class Update(
        val name: String,
        val currency: CurrencyEntity
    ) : EditSourceScreenIntent

    object SaveSource : EditSourceScreenIntent
}

internal sealed class EditSourceScreenSingleEvent : SingleEvent