package com.nstnz.collector.common.feature.main.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.main.presentation.MainScreenViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.multiton

internal val mainScreenDi = DI.Module(name = Routes.Main.name) {
    bind<MainScreenViewModel>() with multiton {
        MainScreenViewModel(
            instance(),
            instance(),
            instance(),
        )
    }
}