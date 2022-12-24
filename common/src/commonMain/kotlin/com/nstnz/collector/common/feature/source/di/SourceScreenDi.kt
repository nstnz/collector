package com.nstnz.collector.common.feature.source.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.source.presentation.SourceScreenViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

internal val sourceScreenDi = DI.Module(name = Routes.Source.name) {
    bind<SourceScreenViewModel>() with factory { sourceId: String ->
        SourceScreenViewModel(sourceId, instance())
    }
}