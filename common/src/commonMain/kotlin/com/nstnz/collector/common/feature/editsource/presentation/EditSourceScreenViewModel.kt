package com.nstnz.collector.common.feature.editsource.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.DeleteSourceDataUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.EditSourceDataUseCase
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourceScenario

internal class EditSourceScreenViewModel(
    private val sourceId: String,
    private val router: Router,
    private val getSourceScenario: GetSourceScenario,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val editSourceDataUseCase: EditSourceDataUseCase,
) : CoroutinesViewModel<EditSourceScreenState, EditSourceScreenIntent, EditSourceScreenSingleEvent>() {

    init {
        sendIntent(EditSourceScreenIntent.Load)
    }

    override fun initialState(): EditSourceScreenState = EditSourceScreenState.Loading

    override fun reduce(
        intent: EditSourceScreenIntent,
        prevState: EditSourceScreenState
    ): EditSourceScreenState = when (intent) {
        is EditSourceScreenIntent.Update -> EditSourceScreenState.Default(
            intent.name,
            intent.currency,
        )
        is EditSourceScreenIntent.ChangeName -> when (prevState) {
            is EditSourceScreenState.Default -> prevState.copy(name = intent.name)
            EditSourceScreenState.Loading -> prevState
        }
        else -> prevState
    }

    override suspend fun performSideEffects(
        intent: EditSourceScreenIntent,
        state: EditSourceScreenState
    ): EditSourceScreenIntent? = when (intent) {
        EditSourceScreenIntent.GoBack -> {
            router.back()
            null
        }
        EditSourceScreenIntent.Load -> {
            val source = getSourceScenario(sourceId)
            val currency = getCurrencyUseCase(source?.originalCurrency?.code)
            currency?.let {
                EditSourceScreenIntent.Update(
                    name = source?.name.orEmpty(),
                    currency = currency
                )
            }
        }
        is EditSourceScreenIntent.Update -> null
        is EditSourceScreenIntent.ChangeCurrency -> {
            if (state is EditSourceScreenState.Default) {
                router.navigateToCurrenciesScreen(
                    multiCheck = false,
                    saveChanges = false,
                    currency = state.currency.code
                )
            }
            null
        }
        is EditSourceScreenIntent.ChangeName -> null
        EditSourceScreenIntent.SaveSource -> {
            if (state is EditSourceScreenState.Default) {
                editSourceDataUseCase(sourceId, state.name, state.currency.code)
                router.back()
            }
            null
        }
        EditSourceScreenIntent.OnResume -> {
            if (state is EditSourceScreenState.Default) {
                val newCurrency = router.getLastResult<CurrencyDomainModel>()
                newCurrency?.let {
                    EditSourceScreenIntent.Update(
                        state.name,
                        newCurrency,
                    )
                }
            } else {
                null
            }
        }
    }
}