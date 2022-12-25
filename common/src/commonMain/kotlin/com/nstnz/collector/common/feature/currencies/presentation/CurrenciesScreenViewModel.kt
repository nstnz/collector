package com.nstnz.collector.common.feature.currencies.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrenciesUseCase

internal class CurrenciesScreenViewModel(
    private val params: CurrenciesViewModelParams,
    private val router: Router,
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
) : CoroutinesViewModel<CurrenciesScreenState, CurrenciesScreenIntent, CurrenciesScreenSingleEvent>() {

    init {
        sendIntent(CurrenciesScreenIntent.LoadCurrencies)
    }

    override fun initialState(): CurrenciesScreenState = CurrenciesScreenState(
        emptyList(), emptyList(), emptyList(), params.multiCheck
    )

    override fun reduce(
        intent: CurrenciesScreenIntent,
        prevState: CurrenciesScreenState
    ): CurrenciesScreenState = when (intent) {
        is CurrenciesScreenIntent.ShowCurrencies -> CurrenciesScreenState(
            list = intent.list,
            checkedCurrencies = if (params.currentCurrency.isNotEmpty()) {
                intent.list.filter { it.code == params.currentCurrency }
            } else {
                intent.list.filter { it.isFavourite }
            },
            multiCheck = params.multiCheck,
            filteredList = intent.filteredList
        )
        else -> prevState
    }

    override suspend fun performSideEffects(
        intent: CurrenciesScreenIntent,
        state: CurrenciesScreenState
    ): CurrenciesScreenIntent? = when (intent) {
        CurrenciesScreenIntent.LoadCurrencies -> {
            val currencies = getCurrenciesUseCase.invoke()
            CurrenciesScreenIntent.ShowCurrencies(
                list = currencies,
                checkedCurrencies = if (params.currentCurrency.isNotEmpty()) {
                    currencies.filter { it.code == params.currentCurrency }
                } else {
                    currencies.filter { it.isFavourite }
                },
                filteredList = currencies
            )
        }
        is CurrenciesScreenIntent.ShowCurrencies -> null
        is CurrenciesScreenIntent.ClickCurrency -> {
            if (params.multiCheck) {
                val list = state.list.firstOrNull { it.code == intent.currency.code }?.let {
                    val index = state.list.indexOf(it)
                    state.list.toMutableList().apply {
                        remove(it)
                        add(index, it.copy(isFavourite = !it.isFavourite))
                    }
                } ?: state.list
                val filteredList =
                    state.filteredList.firstOrNull { it.code == intent.currency.code }?.let {
                        val index = state.filteredList.indexOf(it)
                        state.filteredList.toMutableList().apply {
                            remove(it)
                            add(index, it.copy(isFavourite = !it.isFavourite))
                        }
                    } ?: state.filteredList
                CurrenciesScreenIntent.ShowCurrencies(
                    list = list,
                    checkedCurrencies = list.filter { it.isFavourite },
                    filteredList = filteredList
                )
            } else {
                router.backWithResult(intent.currency)
                null
            }
        }
        is CurrenciesScreenIntent.SearchCurrency -> {
            val filteredList =
                state.list.filter {
                    it.code.contains(intent.searchString, ignoreCase = true) ||
                            it.name.contains(intent.searchString, ignoreCase = true)
                }
            CurrenciesScreenIntent.ShowCurrencies(
                list = state.list,
                checkedCurrencies = if (params.currentCurrency.isNotEmpty()) {
                    state.list.filter { it.code == params.currentCurrency }
                } else {
                    state.list.filter { it.isFavourite }
                },
                filteredList = filteredList
            )
        }
        CurrenciesScreenIntent.GoBack -> {
            router.back()
            null
        }
    }
}