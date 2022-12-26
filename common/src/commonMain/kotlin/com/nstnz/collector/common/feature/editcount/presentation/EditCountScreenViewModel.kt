package com.nstnz.collector.common.feature.editcount.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenIntent
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenState
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.editcount.domain.usecase.DeleteCountDataUseCase
import com.nstnz.collector.common.feature.editcount.domain.usecase.EditCountDataUseCase
import com.nstnz.collector.common.feature.editcount.domain.usecase.GetSourceFundDataUseCase
import com.nstnz.collector.common.feature.editsource.domain.usecase.DeleteSourceDataUseCase
import com.nstnz.collector.common.feature.editsource.domain.usecase.EditSourceDataUseCase
import com.nstnz.collector.common.feature.source.domain.model.SourceFundModel
import com.nstnz.collector.common.feature.source.domain.model.SourceModel
import com.nstnz.collector.common.feature.source.domain.usecase.GetSourceDataUseCase

internal class EditCountScreenViewModel(
    private val sourceFundId: String,
    private val router: Router,
    private val getSourceFundDataUseCase: GetSourceFundDataUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val editCountDataUseCase: EditCountDataUseCase,
    private val deleteCountDataUseCase: DeleteCountDataUseCase,
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
            val sourceFund = getSourceFundDataUseCase(sourceFundId)
            sourceFund?.let { fund ->
                val currency = getCurrencyUseCase(fund.currencyCode)
                currency?.let {
                    EditCountScreenIntent.Update(
                        fund, currency, fund.sum.toString()
                    )
                }
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
        EditCountScreenIntent.Delete -> {
            if (state is EditCountScreenState.Default) {
                deleteCountDataUseCase(sourceFundId)
                router.back()
            }
            null
        }
        EditCountScreenIntent.Save -> {
            if (state is EditCountScreenState.Default) {
                editCountDataUseCase(
                    sourceFundId = sourceFundId,
                    sourceId = state.sourceModel?.sourceId.orEmpty(),
                    currency = state.currency.code,
                    sum = state.sum.toFloatOrNull() ?: 0f,
                    name = state.sourceModel?.name.orEmpty(),
                    default = state.sourceModel?.default ?: false,
                )
                router.back()
            }
            null
        }
        EditCountScreenIntent.OnResume -> {
            if (state is EditCountScreenState.Default) {
                val newCurrency = router.getLastResult<CurrencyEntity>()
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
        EditCountScreenIntent.SelectCurrency -> {
            if (state is EditCountScreenState.Default) {
                router.navigateToCurrenciesScreen(
                    multiCheck = false,
                    saveChanges = false,
                    currency = state.currency.code
                )
            }
            null
        }
    }
}