package com.nstnz.collector.common.feature.converter.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.converter.presentation.ConverterScreenViewModel
import org.kodein.di.*

internal val converterScreenDi = DI.Module(name = Routes.Converter.name) {
    bind<ConverterScreenViewModel>() with multiton {
        ConverterScreenViewModel(instance(), instance())
    }
}