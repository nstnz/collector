package com.nstnz.collector.common.feature.converter.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.converter.presentation.ConverterScreenViewModel
import com.nstnz.collector.common.feature.core.di.coreDi
import org.kodein.di.*

internal val converterScreenDi = DI.Module(name = Routes.Converter.name) {
    bind<ConverterScreenViewModel>() with provider {
        ConverterScreenViewModel(instance())
    }
}