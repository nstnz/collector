package com.nstnz.collector.common.feature.main.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.core.domain.model.SourcesListDomainModel

internal sealed interface MainScreenState : State {
    object Loading : MainScreenState
    data class Default(
        val sourcesMainModel: SourcesListDomainModel,
    ) : MainScreenState
}

internal sealed interface MainScreenIntent : Intent {
    data class ShowSource(val sourceId: String) : MainScreenIntent
    object ShowConverter : MainScreenIntent
    object OnResume : MainScreenIntent
    data class Update(val sourcesMainModel: SourcesListDomainModel) : MainScreenIntent
    object ShowSettingsScreen : MainScreenIntent
    object ShowAddCount : MainScreenIntent
    object ShowAddSource : MainScreenIntent
    data class DeleteSource(val sourceId: String) : MainScreenIntent
}

internal sealed class MainScreenSingleEvent : SingleEvent