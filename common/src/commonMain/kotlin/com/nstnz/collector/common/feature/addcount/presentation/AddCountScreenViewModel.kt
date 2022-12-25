package com.nstnz.collector.common.feature.addcount.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.addcount.domain.SaveCountUseCase
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenIntent
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrenciesUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetMainCurrencyUseCase
import com.nstnz.collector.common.feature.main.domain.usecase.GetSourcesDataUseCase
import com.nstnz.collector.common.feature.source.domain.model.SourceModel
import com.nstnz.collector.common.feature.source.domain.usecase.GetSourceDataUseCase

internal class AddCountScreenViewModel(
    private val sourceId: String,
    private val router: Router,
    private val getSourceDataUseCase: GetSourceDataUseCase,
    private val getSourcesDataUseCase: GetSourcesDataUseCase,
    private val getMainCurrencyUseCase: GetMainCurrencyUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
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
            false
        )
        AddCountScreenIntent.Save -> {
            when (prevState) {
                is AddCountScreenState.Default -> prevState.copy(addMessageShown = true)
                else -> prevState
            }
        }
        else -> prevState
    }

    override suspend fun performSideEffects(
        intent: AddCountScreenIntent,
        state: AddCountScreenState
    ): AddCountScreenIntent? = when (intent) {
        AddCountScreenIntent.Load -> {
            val source = if (sourceId.isNotEmpty()) {
                getSourceDataUseCase(sourceId)
            } else {
                getSourcesDataUseCase().firstOrNull()
            }
            val currency = getCurrencyUseCase(source?.currencyCode) ?: getMainCurrencyUseCase()
            val sum = ""
            AddCountScreenIntent.Update(source, currency, sum)
        }
        is AddCountScreenIntent.Update -> null
        AddCountScreenIntent.GoBack -> {
            router.back()
            null
        }
        AddCountScreenIntent.Save -> {
            if (state is AddCountScreenState.Default) {
                saveCountUseCase(
                    currency = state.currency,
                    sum = state.sum.toFloatOrNull() ?: 0f,
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
        AddCountScreenIntent.SelectSource -> {
            router.navigateToListSourceScreen(sourceId)
            null
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
                val newCurrency = router.getLastResult<CurrencyEntity>()
                val newSource = router.getLastResult<SourceModel>()
                if (newCurrency != null) {
                    AddCountScreenIntent.Update(
                        state.sourceModel,
                        newCurrency,
                        state.sum,
                    )
                } else if (newSource != null) {
                    AddCountScreenIntent.Update(
                        newSource,
                        state.currency,
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