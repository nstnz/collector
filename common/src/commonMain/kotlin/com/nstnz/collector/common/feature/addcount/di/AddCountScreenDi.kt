package com.nstnz.collector.common.feature.addcount.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.core.domain.usecase.SaveCountUseCase
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenViewModel
import com.nstnz.collector.common.feature.addcount.presentation.AddCountViewModelParams
import org.kodein.di.*

internal val addCountScreenDi = DI.Module(name = Routes.AddCount.name) {

    bind<AddCountScreenViewModel>() with multiton { params: AddCountViewModelParams ->
        AddCountScreenViewModel(
            params.sourceId,
            instance(),
            instance(),
            instance(),
            instance(),
        )
    }
}