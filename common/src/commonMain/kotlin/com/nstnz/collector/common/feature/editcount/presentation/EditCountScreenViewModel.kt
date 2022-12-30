package com.nstnz.collector.common.feature.editcount.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.EditCountDataUseCase
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourceCountScenario
import com.nstnz.collector.common.format

internal class EditCountScreenViewModel(
    private val params: EditCountViewModelParams,
    private val router: Router,
    private val getSourceCountDataUseCase: GetSourceCountScenario,
    private val editCountDataUseCase: EditCountDataUseCase,
) : CoroutinesViewModel<EditCountScreenState, EditCountScreenIntent, EditCountScreenSingleEvent>() {

    private val sourceFundId: String
        get() = params.countId.orEmpty()
    private val isAdding: Boolean
        get() = params.isAdding

    init {
        sendIntent(EditCountScreenIntent.Load)
    }

    override fun initialState(): EditCountScreenState = EditCountScreenState.Loading(
        isAdding = isAdding
    )

    override fun reduce(
        intent: EditCountScreenIntent,
        prevState: EditCountScreenState
    ): EditCountScreenState = when (intent) {
        is EditCountScreenIntent.Update -> EditCountScreenState.Default(
            intent.sourceModel,
            intent.currency,
            intent.sum,
            isAdding = isAdding,
            futureTotal = if (isAdding) {
                (intent.sourceModel?.originalSum?.sum ?: 0.0) + (intent.sum.replace(
                    " ",
                    ""
                ).toDoubleOrNull() ?: 0.0)
            } else {
                ((intent.sourceModel?.originalSum?.sum ?: 0.0) - (intent.sum.replace(
                    " ",
                    ""
                ).toDoubleOrNull() ?: 0.0)).takeIf { it >= 0.0 } ?: 0.0
            }
        )
        else -> prevState
    }

    override suspend fun performSideEffects(
        intent: EditCountScreenIntent,
        state: EditCountScreenState
    ): EditCountScreenIntent? = when (intent) {
        EditCountScreenIntent.GoBack -> {
            router.back()
            null
        }
        EditCountScreenIntent.Load -> {
            val sourceFund = getSourceCountDataUseCase(sourceFundId, null)
            sourceFund?.let { fund ->
                EditCountScreenIntent.Update(
                    fund, fund.originalSum.currency, "",
                    isAdding = isAdding
                )
            }
        }
        is EditCountScreenIntent.Update -> null
        is EditCountScreenIntent.ChangeSum -> {
            if (state is EditCountScreenState.Default) {
                EditCountScreenIntent.Update(
                    state.sourceModel,
                    state.currency,
                    intent.sum,
                    isAdding = isAdding
                )
            } else {
                null
            }
        }
        EditCountScreenIntent.Save -> {
            if (state is EditCountScreenState.Default) {
                editCountDataUseCase(
                    sourceFundId = sourceFundId,
                    sourceId = state.sourceModel?.sourceId.orEmpty(),
                    currency = state.currency.code,
                    sum = state.futureTotal,
                    name = "",
                    default = state.sourceModel?.isDefault ?: false,
                )
                router.back()
            }
            null
        }
        EditCountScreenIntent.OnResume -> {
            if (state is EditCountScreenState.Default) {
                val newCurrency = router.getLastResult<CurrencyDomainModel>()
                if (newCurrency != null) {
                    EditCountScreenIntent.Update(
                        state.sourceModel,
                        newCurrency,
                        state.sum,
                        isAdding = isAdding
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