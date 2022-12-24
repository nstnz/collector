package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.source.domain.scenario.GetSourceScenario

internal class SourceScreenViewModel(
    private val sourceId: String,
    private val router: Router,
    private val getSourceScenario: GetSourceScenario,
) : CoroutinesViewModel<SourceScreenState, SourceScreenIntent, SourceScreenSingleEvent>() {

    override fun initialState(): SourceScreenState = SourceScreenState.Loading(sourceId)

    override fun reduce(
        intent: SourceScreenIntent,
        prevState: SourceScreenState
    ): SourceScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: SourceScreenIntent,
        state: SourceScreenState
    ): SourceScreenIntent? = when (intent) {
        is SourceScreenIntent.ShowAddCount -> {
            router.navigateToAddCountScreen(sourceId)
            null
        }
    }
}