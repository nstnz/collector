package com.nstnz.collector.common.feature.addsource.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.addsource.domain.usecase.SaveSourceDataUseCase
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenViewModel
import org.kodein.di.*

internal val addSourceScreenDi = DI.Module(name = Routes.AddSource.name) {

    bind<SaveSourceDataUseCase>() with provider {
        SaveSourceDataUseCase(
            instance(),
            instance(),
        )
    }
    bind<AddSourceScreenViewModel>() with multiton {
        AddSourceScreenViewModel(
            instance(),
            instance(),
            instance(),
        )
    }
}