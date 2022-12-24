package com.nstnz.collector.common.feature.main.presentation

import com.nstnz.collector.common.basic.presentation.Intent
import com.nstnz.collector.common.basic.presentation.SingleEvent
import com.nstnz.collector.common.basic.presentation.State
import com.nstnz.collector.common.feature.main.domain.model.SourcesMainModel

internal sealed interface MainScreenState : State {
    object Loading : MainScreenState
    data class Default(val sourcesMainModel: SourcesMainModel) : MainScreenState
}

internal sealed interface MainScreenIntent : Intent {
    data class ShowSource(val sourceId: String) : MainScreenIntent
    object ShowConverter : MainScreenIntent
    object Load : MainScreenIntent
    data class Update(val sourcesMainModel: SourcesMainModel) : MainScreenIntent
    object ShowSettingsScreen : MainScreenIntent
}

internal sealed class MainScreenSingleEvent : SingleEvent