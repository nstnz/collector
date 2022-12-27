package com.nstnz.collector.common.feature.editcount.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.DeleteCountDataUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.EditCountDataUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetSourceCountDataUseCase

internal class EditCountScreenViewModel(
    private val sourceFundId: String,
    private val router: Router,
    private val getSourceCountDataUseCase: GetSourceCountDataUseCase,
    private val editCountDataUseCase: EditCountDataUseCase,
) : CoroutinesViewModel<EditCountScreenState, EditCountScreenIntent, EditCountScreenSingleEvent>() {

    init {
        sendIntent(EditCountScreenIntent.Load)
    }

    override fun initialState(): EditCountScreenState = EditCountScreenState.Loading

    override fun reduce(
        intent: EditCountScreenIntent,
        prevState: EditCountScreenState
    ): EditCountScreenState = when (intent) {
        is EditCountScreenIntent.Update -> EditCountScreenState.Default(
            intent.sourceModel,
            intent.currency,
            intent.sum,
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
            val sourceFund = getSourceCountDataUseCase(sourceFundId)
            sourceFund?.let { fund ->
                EditCountScreenIntent.Update(
                    fund, fund.originalSum.currency, fund.originalSum.sum.toString()
                )
            }
        }
        is EditCountScreenIntent.Update -> null
        is EditCountScreenIntent.ChangeSum -> {
            if (state is EditCountScreenState.Default) {
                //todo format sum
                EditCountScreenIntent.Update(
                    state.sourceModel,
                    state.currency,
                    intent.sum,
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
                    sum = state.sum.toDoubleOrNull() ?: 0.0,
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