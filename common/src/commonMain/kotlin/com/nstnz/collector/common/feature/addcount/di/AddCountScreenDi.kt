package com.nstnz.collector.common.feature.addcount.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.addcount.domain.SaveCountUseCase
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenViewModel
import com.nstnz.collector.common.feature.addcount.presentation.AddCountViewModelParams
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenViewModel
import com.nstnz.collector.common.feature.converter.domain.usecase.GetExchangeRatesUseCase
import org.kodein.di.*

internal val addCountScreenDi = DI.Module(name = Routes.AddCount.name) {

    bind<SaveCountUseCase>() with provider {
        SaveCountUseCase(
            instance(),
            instance()
        )
    }

    bind<AddCountScreenViewModel>() with multiton { params: AddCountViewModelParams ->
        AddCountScreenViewModel(
            params.sourceId,
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
        )
    }
}