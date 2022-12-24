package com.nstnz.collector.common.feature.addcount.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router

internal class AddCountScreenViewModel(
    private val sourceId: String,
    private val router: Router,
) : CoroutinesViewModel<AddCountScreenState, AddCountScreenIntent, AddCountScreenSingleEvent>() {

    override fun initialState(): AddCountScreenState = AddCountScreenState.Default

    override fun reduce(
        intent: AddCountScreenIntent,
        prevState: AddCountScreenState
    ): AddCountScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: AddCountScreenIntent,
        state: AddCountScreenState
    ): AddCountScreenIntent? {
        return null
    }
}