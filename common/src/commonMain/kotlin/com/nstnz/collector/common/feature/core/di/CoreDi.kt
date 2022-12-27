package com.nstnz.collector.common.feature.core.di

import com.nstnz.collector.common.feature.core.domain.scenario.GetSourceCountScenario
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourceCountsScenario
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourceScenario
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourcesListScenario
import com.nstnz.collector.common.feature.core.domain.scenario.GetSourcesScenario
import com.nstnz.collector.common.feature.core.domain.usecase.*
import com.nstnz.collector.common.feature.core.domain.usecase.GetCurrencyUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetExchangeRatesUseCase
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
    bind<SaveCountUseCase>() with provider {
        SaveCountUseCase(
            instance(),
            instance()
        )
    }
    bind<DeleteSourceDataUseCase>() with provider {
        DeleteSourceDataUseCase(
            instance(),
            instance(),
        )
    }
    bind<EditSourceDataUseCase>() with provider {
        EditSourceDataUseCase(
            instance(),
            instance(),
        )
    }
    bind<DeleteCountDataUseCase>() with provider {
        DeleteCountDataUseCase(
            instance(),
            instance(),
        )
    }
    bind<EditCountDataUseCase>() with provider {
        EditCountDataUseCase(
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
    bind<GetSourceCountsScenario>() with provider {
        GetSourceCountsScenario(
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
    bind<GetSourcesScenario>() with provider {
        GetSourcesScenario(
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
    bind<GetSourceCountScenario>() with provider {
        GetSourceCountScenario(
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