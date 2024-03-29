package com.nstnz.collector.common.feature.addsource.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.usecase.SaveSourceDataUseCase
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetDefaultCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase

internal class AddSourceScreenViewModel(
    private val router: Router,
    private val saveSourceDataUseCase: SaveSourceDataUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase
) : CoroutinesViewModel<AddSourceScreenState, AddSourceScreenIntent, AddSourceScreenSingleEvent>() {

    init {
        sendIntent(AddSourceScreenIntent.Load)
    }

    override fun initialState(): AddSourceScreenState = AddSourceScreenState.Loading

    override fun reduce(
        intent: AddSourceScreenIntent,
        prevState: AddSourceScreenState
    ): AddSourceScreenState = when (intent) {
        is AddSourceScreenIntent.Update -> AddSourceScreenState.Default(
            intent.name,
            intent.currency,
        )
        is AddSourceScreenIntent.ChangeName -> when (prevState) {
            is AddSourceScreenState.Default -> prevState.copy(
                name = intent.name,
                showNameError = false
            )
            AddSourceScreenState.Loading -> prevState
        }
        is AddSourceScreenIntent.SaveSource -> when (prevState) {
            is AddSourceScreenState.Default -> prevState.copy(
                showNameError = prevState.name.isEmpty()
            )
            AddSourceScreenState.Loading -> prevState
        }
        else -> prevState
    }

    override suspend fun performSideEffects(
        intent: AddSourceScreenIntent,
        state: AddSourceScreenState
    ): AddSourceScreenIntent? = when (intent) {
        AddSourceScreenIntent.GoBack -> {
            router.back()
            null
        }
        is AddSourceScreenIntent.SaveSource -> {
            if (state is AddSourceScreenState.Default) {
                if (state.name.isNotEmpty()) {
                    saveSourceDataUseCase(state.name, state.currency.code)
                    router.back()
                }
            }
            null
        }
        is AddSourceScreenIntent.ChangeCurrency -> {
            if (state is AddSourceScreenState.Default) {
                router.navigateToCurrenciesScreen(
                    multiCheck = false,
                    saveChanges = false,
                    currency = state.currency.code
                )
            }
            null
        }
        AddSourceScreenIntent.Load -> {
            val currency = getDefaultCurrencyUseCase()
            currency?.let {
                AddSourceScreenIntent.Update(
                    "", currency
                )
            }
        }
        is AddSourceScreenIntent.Update -> null
        is AddSourceScreenIntent.ChangeName -> null
        AddSourceScreenIntent.OnResume -> {
            if (state is AddSourceScreenState.Default) {
                val newCurrency = router.getLastResult<CurrencyDomainModel>()
                newCurrency?.let {
                    AddSourceScreenIntent.Update(
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