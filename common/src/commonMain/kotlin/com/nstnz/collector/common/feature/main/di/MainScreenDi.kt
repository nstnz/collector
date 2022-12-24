package com.nstnz.collector.common.feature.main.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.currencies.domain.usecase.GetMainCurrencyUseCase
import com.nstnz.collector.common.feature.main.domain.scenario.GetSourcesScenario
import com.nstnz.collector.common.feature.main.domain.usecase.GetSourcesDataUseCase
import com.nstnz.collector.common.feature.main.presentation.MainScreenViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal val mainScreenDi = DI.Module(name = Routes.Main.name) {
    bind<GetSourcesScenario>() with provider {
        GetSourcesScenario(
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
    bind<GetSourcesDataUseCase>() with provider {
        GetSourcesDataUseCase(
            instance(),
            instance(),
        )
    }

    bind<MainScreenViewModel>() with provider {
        MainScreenViewModel(instance(), instance())
    }
}