package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourceScenario
import com.nstnz.collector.common.feature.core.domain.usecase.DeleteCountDataUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetDefaultCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetSourcePointsUseCase
import com.nstnz.collector.common.feature.main.presentation.MainScreenIntent
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope

internal class SourceScreenViewModel(
    private val sourceId: String,
    private val router: Router,
    private val getSourceScenario: GetSourceScenario,
    private val getSourcePointsUseCase: GetSourcePointsUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
    private val deleteCountDataUseCase: DeleteCountDataUseCase
) : CoroutinesViewModel<SourceScreenState, SourceScreenIntent, SourceScreenSingleEvent>() {

    private var points: List<Double> = emptyList()

    override fun initialState(): SourceScreenState = SourceScreenState.Loading

    override fun reduce(
        intent: SourceScreenIntent,
        prevState: SourceScreenState
    ): SourceScreenState =
        when (intent) {
            is SourceScreenIntent.Update -> {
                if (prevState is SourceScreenState.Default) {
                    prevState.copy(sourceMainModel = intent.sourceMainModel)
                } else {
                    SourceScreenState.Default(intent.sourceMainModel)
                }
            }
            is SourceScreenIntent.UpdatePoints -> {
                if (prevState is SourceScreenState.Default) {
                    prevState.copy(points = intent.points, favCode = intent.favCode)
                } else {
                    prevState
                }
            }
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
        SourceScreenIntent.OnResume -> {
            val source = getSourceScenario(sourceId)
            source?.let {
                SourceScreenIntent.Update(it)
            }
        }
        is SourceScreenIntent.Update -> {
            if (points.isEmpty()) {
                val defaultCurrency = getDefaultCurrencyUseCase()
                val points = getSourcePointsUseCase(
                    currency = defaultCurrency?.code.orEmpty(),
                    originCurrency = intent.sourceMainModel.originalCurrency.code,
                    sum = intent.sourceMainModel.originalSum.sum,
                )
                sendIntent(
                    SourceScreenIntent.UpdatePoints(
                        points,
                        defaultCurrency?.codeToShow.orEmpty()
                    )
                )
            }
            null
        }
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
        is SourceScreenIntent.ShowCount -> {
            router.navigateToEditSourceFundScreen(intent.sourceFundId)
            null
        }
        is SourceScreenIntent.ShowAddCurrency -> {
            router.navigateToCurrenciesScreen(multiCheck = true, saveChanges = true, null)
            null
        }
        SourceScreenIntent.ChangeShownCurrency -> {
            if (state is SourceScreenState.Default) {
                router.navigateToCurrenciesScreen(
                    multiCheck = false,
                    saveChanges = false,
                    currency = state.sourceMainModel.originalCurrency.code
                )
            }
            null
        }
        is SourceScreenIntent.DeleteCount -> {
            deleteCountDataUseCase(intent.sourceFundId)
            SourceScreenIntent.OnResume
        }
        is SourceScreenIntent.UpdatePoints -> null
    }
}