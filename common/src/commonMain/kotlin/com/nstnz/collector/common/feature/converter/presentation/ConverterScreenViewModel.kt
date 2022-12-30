package com.nstnz.collector.common.feature.converter.presentation

import com.nstnz.collector.common.basic.presentation.CoroutinesViewModel
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.usecase.GetDefaultCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetExchangeRatesUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope

private const val ChangeFirstCurrency = "ChangeFirstCurrency"
private const val ChangeSecondCurrency = "ChangeSecondCurrency"

internal class ConverterScreenViewModel(
    private val router: Router,
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase
) : CoroutinesViewModel<ConverterScreenState, ConverterScreenIntent, ConverterScreenSingleEvent>() {

    private var actualCurrency1: CurrencyDomainModel? = null
    private var actualCurrency2: CurrencyDomainModel? = null

    override fun initialState(): ConverterScreenState = ConverterScreenState(
        sum = "",
        currency = null,
        exchange = null
    )

    override fun reduce(
        intent: ConverterScreenIntent,
        prevState: ConverterScreenState
    ): ConverterScreenState = when (intent) {
        is ConverterScreenIntent.Update -> prevState.copy(
            sum = intent.sum,
            currency = intent.currency,
            exchange = intent.exchange
        )
        else -> prevState
    }

    override suspend fun performSideEffects(
        intent: ConverterScreenIntent,
        state: ConverterScreenState
    ): ConverterScreenIntent? = when (intent) {
        ConverterScreenIntent.ShowMainScreen -> {
            router.navigateToMainScreen()
            null
        }
        ConverterScreenIntent.ChangeFirstCurrency -> {
            router.setExpectedKey(ChangeFirstCurrency)
            router.navigateToCurrenciesScreen(
                multiCheck = false,
                saveChanges = false,
                currency = state.currency?.code
            )
            null
        }
        ConverterScreenIntent.ChangeSecondCurrency -> {
            router.setExpectedKey(ChangeSecondCurrency)
            router.navigateToCurrenciesScreen(
                multiCheck = false,
                saveChanges = false,
                currency = state.exchange?.currency?.code
            )
            null
        }
        ConverterScreenIntent.ShowSettingsScreen -> {
            router.navigateToSettingsScreen()
            null
        }
        is ConverterScreenIntent.ChangeSum -> {
            val exchangeList = getExchangeRatesUseCase(
                originCurrencyCode = state.currency?.code.orEmpty(),
                sum = intent.sum.replace(" ", "").toDoubleOrNull() ?: 0.0,
                currencies = listOf(actualCurrency2?.code.orEmpty())
            )
            ConverterScreenIntent.Update(
                intent.sum,
                state.currency,
                exchangeList.firstOrNull()
            )
        }
        ConverterScreenIntent.OnResume -> {
            val tmpActualCurrency1 = getDefaultCurrencyUseCase()
            val tmpActualCurrency2 = getFavoriteCurrenciesUseCase().let {
                it.firstOrNull { !it.isDefault } ?: it.firstOrNull()
            }

            actualCurrency1 =
                router.getLastResult<CurrencyDomainModel>(ChangeFirstCurrency) ?: actualCurrency1
                        ?: tmpActualCurrency1
            actualCurrency2 =
                router.getLastResult<CurrencyDomainModel>(ChangeSecondCurrency) ?: actualCurrency2
                        ?: tmpActualCurrency2

            val exchangeList = getExchangeRatesUseCase(
                originCurrencyCode = actualCurrency1?.code.orEmpty(),
                sum = state.sum.replace(" ", "").toDoubleOrNull() ?: 0.0,
                currencies = listOf(actualCurrency2?.code.orEmpty())
            )
            ConverterScreenIntent.Update(
                state.sum,
                actualCurrency1,
                exchangeList.firstOrNull()
            )
        }
        is ConverterScreenIntent.Update -> null
    }
}