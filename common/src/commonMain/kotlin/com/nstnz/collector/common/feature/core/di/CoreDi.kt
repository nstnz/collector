package com.nstnz.collector.common.feature.core.di

import com.nstnz.collector.common.feature.core.domain.scenario.GetSourcesListScenario
import com.nstnz.collector.common.feature.core.domain.usecase.*
import com.nstnz.collector.common.feature.core.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetExchangeRatesUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetSourceCountsUseCase
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourceScenario
import com.nstnz.collector.common.feature.core.domain.usecase.GetSourcesDataUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal val coreDi = DI.Module(name = "Core") {
    bind<GetSourcesListScenario>() with provider {
        GetSourcesListScenario(
            instance(),
            instance(),
            instance(),
        )
    }
    bind<SaveSourceDataUseCase>() with provider {
        SaveSourceDataUseCase(
            instance(),
            instance(),
        )
    }
    bind<GetCurrencyUseCase>() with provider {
        GetCurrencyUseCase(
            instance(),
            instance(),
        )
    }
    bind<GetSourceCountsUseCase>() with provider {
        GetSourceCountsUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
        )
    }
    bind<GetSourceScenario>() with provider {
        GetSourceScenario(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
        )
    }
    bind<GetSourcesDataUseCase>() with provider {
        GetSourcesDataUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
        )
    }
    bind<GetFavoriteCurrenciesUseCase>() with provider {
        GetFavoriteCurrenciesUseCase(
            instance(),
            instance(),
        )
    }
    bind<GetSourceCountDataUseCase>() with provider {
        GetSourceCountDataUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
        )
    }
    bind<GetExchangeRatesUseCase>() with provider {
        GetExchangeRatesUseCase(
            instance(),
            instance(),
            instance()
        )
    }
}