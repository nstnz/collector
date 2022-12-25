package com.nstnz.collector.common.feature.addcount.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrenciesUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetMainCurrencyUseCase
import com.nstnz.collector.common.feature.main.domain.usecase.GetSourcesDataUseCase
import com.nstnz.collector.common.feature.source.domain.usecase.GetSourceDataUseCase

internal class AddCountScreenViewModel(
    private val sourceId: String,
    private val sourceFundId: String,
    private val router: Router,
    private val getSourceDataUseCase: GetSourceDataUseCase,
    private val getSourcesDataUseCase: GetSourcesDataUseCase,
    private val getMainCurrencyUseCase: GetMainCurrencyUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
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
            intent.fund,
        )
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
            val fund = source?.funds?.firstOrNull { it.id == sourceFundId }
                ?: source?.funds?.firstOrNull { it.isDefault }
            val currency = getCurrencyUseCase(fund?.currencyCode) ?: getMainCurrencyUseCase()
            val sum = ""
            AddCountScreenIntent.Update(source, currency, sum, fund)
        }
        is AddCountScreenIntent.Update -> null
        AddCountScreenIntent.GoBack -> {
            router.back()
            null
        }
        is AddCountScreenIntent.Save -> {
            //todo save
            router.back()
            null
        }
        is AddCountScreenIntent.ChangeSum -> {
            if (state is AddCountScreenState.Default) {
                //todo format sum
                AddCountScreenIntent.Update(
                    state.sourceModel,
                    state.currency,
                    intent.sum,
                    state.fund
                )
            } else {
                null
            }
        }
        AddCountScreenIntent.SelectSource -> {
            router.navigateToListSourceScreen(sourceId, sourceFundId)
            null
        }
        AddCountScreenIntent.OnResume -> {
            if (state is AddCountScreenState.Default) {
                val newCurrency = router.getLastResult<CurrencyEntity>()
                newCurrency?.let {
                    AddCountScreenIntent.Update(
                        state.sourceModel,
                        newCurrency,
                        state.sum,
                        state.fund
                    )
                }
            } else {
                null
            }
        }
    }
}