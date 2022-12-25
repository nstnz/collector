package com.nstnz.collector.common.feature.addcount.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenViewModel
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenViewModel
import org.kodein.di.*

internal val addCountScreenDi = DI.Module(name = Routes.AddCount.name) {

    bind<AddCountScreenViewModel>() with multiton { sourceId: String ->
        AddCountScreenViewModel(
            sourceId,
            instance(),
            instance(),
            instance(),
            instance(),
        )
    }
}