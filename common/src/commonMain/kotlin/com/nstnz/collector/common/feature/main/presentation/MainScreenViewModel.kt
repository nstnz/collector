package com.nstnz.collector.common.feature.main.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.feature.auth.domain.UseCase2

internal class MainScreenViewModel(
    private val useCase2: UseCase2
) : CoroutinesViewModel<MainScreenState, MainScreenIntent, MainScreenSingleEvent>() {

    override fun initialState(): MainScreenState = MainScreenState

    override fun reduce(intent: MainScreenIntent, prevState: MainScreenState): MainScreenState =
        prevState

    override suspend fun performSideEffects(
        intent: MainScreenIntent,
        state: MainScreenState
    ): MainScreenIntent? {
        println("OLOLOLO = " + useCase2.hashCode().toString())
        return null
    }
}