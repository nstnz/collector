package com.nstnz.collector.common.feature.addsource.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.addsource.domain.usecase.SaveSourceDataUseCase
import com.nstnz.collector.common.feature.source.domain.scenario.GetSourceScenario
import com.nstnz.collector.common.feature.source.presentation.SourceScreenIntent
import com.nstnz.collector.common.feature.source.presentation.SourceScreenSingleEvent

internal class AddSourceScreenViewModel(
    private val router: Router,
    private val saveSourceDataUseCase: SaveSourceDataUseCase,
) : CoroutinesViewModel<AddSourceScreenState, AddSourceScreenIntent, AddSourceScreenSingleEvent>() {

    override fun initialState(): AddSourceScreenState = AddSourceScreenState.Default

    override fun reduce(
        intent: AddSourceScreenIntent,
        prevState: AddSourceScreenState
    ): AddSourceScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: AddSourceScreenIntent,
        state: AddSourceScreenState
    ): AddSourceScreenIntent? = when (intent) {
        AddSourceScreenIntent.GoBack -> {
            router.back()
            null
        }
        is AddSourceScreenIntent.SaveSource -> {
            val sourceId = saveSourceDataUseCase(intent.name)
            router.navigateToSourceScreen(sourceId, clearBackEntry = true)
            null
        }
    }
}