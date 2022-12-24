package com.nstnz.collector.common.feature.source.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.source.presentation.SourceScreenViewModel
import org.kodein.di.*

internal val sourceScreenDi = DI.Module(name = Routes.Source.name) {
    bind<SourceScreenViewModel>() with multiton { sourceId: String ->
        SourceScreenViewModel(sourceId, instance())
    }
}