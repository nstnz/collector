package com.nstnz.collector.common.feature.listsource.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.main.domain.usecase.GetSourcesDataUseCase

internal class ListSourceScreenViewModel(
    private val sourceId: String,
    private val sourceFundId: String,
    private val router: Router,
    private val getSourcesDataUseCase: GetSourcesDataUseCase,
) : CoroutinesViewModel<ListSourceScreenState, ListSourceScreenIntent, ListSourceScreenSingleEvent>() {

    override fun initialState(): ListSourceScreenState = ListSourceScreenState.Loading

    override fun reduce(
        intent: ListSourceScreenIntent,
        prevState: ListSourceScreenState
    ): ListSourceScreenState = when (intent) {
        is ListSourceScreenIntent.Update -> ListSourceScreenState.Default(
            intent.sources,
            sourceId,
        )
        else -> prevState
    }

    override suspend fun performSideEffects(
        intent: ListSourceScreenIntent,
        state: ListSourceScreenState
    ): ListSourceScreenIntent? = when (intent) {
        ListSourceScreenIntent.GoBack -> {
            router.back()
            null
        }
        is ListSourceScreenIntent.SelectSourceAndFund -> {
            router.backWithResult(intent.sourceModel)
            null
        }
        ListSourceScreenIntent.Load -> {
            val sources = getSourcesDataUseCase()
            ListSourceScreenIntent.Update(sources)
        }
        is ListSourceScreenIntent.Update -> null
    }
}