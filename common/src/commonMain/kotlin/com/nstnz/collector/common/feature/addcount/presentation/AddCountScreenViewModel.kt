package com.nstnz.collector.common.feature.addcount.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.usecase.SaveCountUseCase
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourceScenario
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourcesScenario

internal class AddCountScreenViewModel(
    private val sourceId: String,
    private val router: Router,
    private val getSourceScenario: GetSourceScenario,
    private val getSourcesDataUseCase: GetSourcesScenario,
    private val saveCountUseCase: SaveCountUseCase,
) : CoroutinesViewModel<AddCountScreenState, AddCountScreenIntent, AddCountScreenSingleEvent>() {

    init {
        sendIntent(AddCountScreenIntent.Load)
    }

    override fun initialState(): AddCountScreenState = AddCountScreenState.Loading

    override fun reduce(
        intent: AddCountScreenIntent,
        prevState: AddCountScreenState
    ): AddCountScreenState = when (intent) {
        is AddCountScreenIntent.Update -> AddCountScreenState.Default(
            intent.sourceModel,
            intent.currency,
            intent.sum,
        )
        else -> prevState
    }

    override suspend fun performSideEffects(
        intent: AddCountScreenIntent,
        state: AddCountScreenState
    ): AddCountScreenIntent? = when (intent) {
        AddCountScreenIntent.Load -> {
            val source = if (sourceId.isNotEmpty()) {
                getSourceScenario(sourceId)
            } else {
                getSourcesDataUseCase().firstOrNull()
            }
            val sum = ""
            AddCountScreenIntent.Update(source, source?.originalCurrency!!, sum)
        }
        is AddCountScreenIntent.Update -> null
        AddCountScreenIntent.GoBack -> {
            router.back()
            null
        }
        AddCountScreenIntent.Save -> {
            if (state is AddCountScreenState.Default) {
                saveCountUseCase(
                    currency = state.currency.code,
                    sum = state.sum.toDoubleOrNull() ?: 0.0,
                    sourceId = sourceId
                )
                router.back()
            }
            null
        }
        is AddCountScreenIntent.ChangeSum -> {
            if (state is AddCountScreenState.Default) {
                //todo format sum
                AddCountScreenIntent.Update(
                    state.sourceModel,
                    state.currency,
                    intent.sum,
                )
            } else {
                null
            }
        }
        AddCountScreenIntent.SelectCurrency -> {
            if (state is AddCountScreenState.Default) {
                router.navigateToCurrenciesScreen(
                    multiCheck = false,
                    saveChanges = false,
                    currency = state.currency.code
                )
            }
            null
        }
        AddCountScreenIntent.OnResume -> {
            if (state is AddCountScreenState.Default) {
                val newCurrency = router.getLastResult<CurrencyDomainModel>()
                if (newCurrency != null) {
                    AddCountScreenIntent.Update(
                        state.sourceModel,
                        newCurrency,
                        state.sum,
                    )
                } else {
                    null
                }
            } else {
                null
            }
        }
    }
}