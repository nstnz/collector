package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.SourceDomainModel

internal sealed interface SourceScreenState : State {
    object Loading : SourceScreenState

    data class Default(
        val sourceMainModel: SourceDomainModel,
        val points: List<Double> = emptyList(),
        val favCode: String = ""
    ) : SourceScreenState
}

internal sealed interface SourceScreenIntent : Intent {
    object OnResume : SourceScreenIntent
    object GoBack : SourceScreenIntent
    object EditSource : SourceScreenIntent
    object ChangeShownCurrency : SourceScreenIntent
    object ShowAddCurrency : SourceScreenIntent
    data class ShowCount(val sourceFundId: String) : SourceScreenIntent
    data class DeleteCount(val sourceFundId: String) : SourceScreenIntent
    object AddCount : SourceScreenIntent
    data class ShowAddCount(val sourceId: String) : SourceScreenIntent
    data class Update(val sourceMainModel: SourceDomainModel) : SourceScreenIntent
    data class UpdatePoints(
        val points: List<Double>,
        val favCode: String = ""
    ) : SourceScreenIntent
}

internal sealed class SourceScreenSingleEvent : SingleEvent