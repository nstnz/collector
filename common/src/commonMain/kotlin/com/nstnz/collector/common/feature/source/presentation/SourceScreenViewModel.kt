package com.nstnz.collector.common.feature.source.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourceScenario
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.editcount.domain.usecase.DeleteCountDataUseCase

internal class SourceScreenViewModel(
    private val sourceId: String,
    private val router: Router,
    private val getSourceScenario: GetSourceScenario,
    private val deleteCountDataUseCase: DeleteCountDataUseCase
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
        SourceScreenIntent.OnResume -> {
            val source = getSourceScenario(sourceId, null)
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
        is SourceScreenIntent.ShowCount -> {
            router.navigateToEditSourceFundScreen(intent.sourceFundId)
            null
        }
        SourceScreenIntent.ChangeShownCurrency -> {
            if (state is  SourceScreenState.Default) {
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
    }
}