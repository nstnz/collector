package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.main.domain.scenario.GetSourcesScenario

internal class SourceScreenViewModel(
    private val sourceId: String,
    private val router: Router,
    private val getSourcesScenario: GetSourcesScenario,
) : CoroutinesViewModel<SourceScreenState, SourceScreenIntent, SourceScreenSingleEvent>() {

    override fun initialState(): SourceScreenState = SourceScreenState.Loading

    override fun reduce(
        intent: SourceScreenIntent,
        prevState: SourceScreenState
    ): SourceScreenState =
        when (intent) {
            is SourceScreenIntent.Update -> SourceScreenState.Default(intent.sourceMainModel)
            else -> prevState
        }

    override suspend fun performSideEffects(
        intent: SourceScreenIntent,
        state: SourceScreenState
    ): SourceScreenIntent? = when (intent) {
        is SourceScreenIntent.ShowAddCount -> {
            router.navigateToAddCountScreen(sourceId)
            null
        }
        SourceScreenIntent.Load -> {
            val source = getSourcesScenario().sources.firstOrNull {
                it.id == sourceId
            }
            source?.let {
                SourceScreenIntent.Update(it)
            }
        }
        is SourceScreenIntent.Update -> null
        SourceScreenIntent.AddCount -> {
            router.navigateToAddCountScreen(sourceId)
            null
        }
        SourceScreenIntent.GoBack -> {
            router.back()
            null
        }
        SourceScreenIntent.EditSource -> {
            router.navigateToEditSourceScreen(sourceId)
            null
        }
    }
}