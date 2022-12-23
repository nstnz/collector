package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.auth.domain.UseCase2

internal class SourceScreenViewModel(
    private val sourceId: String,
    private val router: Router
) : CoroutinesViewModel<SourceScreenState, SourceScreenIntent, SourceScreenSingleEvent>() {

    override fun initialState(): SourceScreenState = SourceScreenState.Loading(sourceId)

    override fun reduce(intent: SourceScreenIntent, prevState: SourceScreenState): SourceScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: SourceScreenIntent,
        state: SourceScreenState
    ): SourceScreenIntent? {
        println("OLOLOLO = " + sourceId)
        return null
    }
}