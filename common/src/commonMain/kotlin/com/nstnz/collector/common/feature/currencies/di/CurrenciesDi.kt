package com.nstnz.collector.common.feature.currencies.di

import com.nstnz.collector.common.feature.currencies.data.CurrenciesRepository
import com.nstnz.collector.common.feature.currencies.data.db.datasource.CurrenciesDbDataSource
import com.nstnz.collector.common.feature.currencies.data.network.datasource.CurrenciesNetworkDataSource
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetCurrenciesUseCase
import com.nstnz.collector.common.feature.currencies.data.prefs.CurrenciesPrefs
import com.nstnz.collector.common.feature.core.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetFavoriteCurrenciesUseCase
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
    bind<CurrenciesPrefs>() with provider {
        CurrenciesPrefs(instance())
    }

    bind<CurrenciesRepository>() with singleton {
        CurrenciesRepository(
            instance(),
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