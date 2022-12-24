package com.nstnz.collector.common.feature.currencies.di

import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import com.nstnz.collector.common.feature.currencies.data.db.datasource.CurrenciesDbDataSource
import com.nstnz.collector.common.feature.currencies.data.network.datasource.CurrenciesNetworkDataSource
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrenciesUseCase
import com.nstnz.collector.common.feature.converter.domain.usecase.GetExchangeRatesUseCase
import com.nstnz.collector.common.feature.currencies.domain.usecase.RefreshCurrenciesUseCase
import com.nstnz.collector.common.feature.currencies.presentation.CurrenciesScreenViewModel
import com.nstnz.collector.common.feature.currencies.presentation.CurrenciesViewModelParams
import org.kodein.di.*

internal val currenciesDi = DI.Module(name = "Currencies") {

    bind<CurrenciesNetworkDataSource>() with provider {
        CurrenciesNetworkDataSource(
            instance(),
            instance()
        )
    }
    bind<CurrenciesDbDataSource>() with provider {
        CurrenciesDbDataSource(
            instance()
        )
    }

    bind<CurrenciesRepository>() with provider {
        CurrenciesRepository(
            instance(),
            instance()
        )
    }

    bind<GetExchangeRatesUseCase>() with provider {
        GetExchangeRatesUseCase(
            instance(),
            instance()
        )
    }
    bind<GetCurrenciesUseCase>() with provider {
        GetCurrenciesUseCase(
            instance(),
            instance()
        )
    }
    bind<RefreshCurrenciesUseCase>() with provider {
        RefreshCurrenciesUseCase(
            instance(),
            instance()
        )
    }

    bind<CurrenciesScreenViewModel>() with multiton { params: CurrenciesViewModelParams ->
        CurrenciesScreenViewModel(params, instance(), instance())
    }
}