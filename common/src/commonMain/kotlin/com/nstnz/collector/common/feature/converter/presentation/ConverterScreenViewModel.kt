package com.nstnz.collector.common.feature.converter.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router

internal class ConverterScreenViewModel(
    private val router: Router
) : CoroutinesViewModel<ConverterScreenState, ConverterScreenIntent, ConverterScreenSingleEvent>() {

    override fun initialState(): ConverterScreenState = ConverterScreenState

    override fun reduce(
        intent: ConverterScreenIntent,
        prevState: ConverterScreenState
    ): ConverterScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: ConverterScreenIntent,
        state: ConverterScreenState
    ): ConverterScreenIntent? = when (intent) {
        ConverterScreenIntent.ShowMainScreen -> {
            router.navigateToMainScreen()
            null
        }
    }
}