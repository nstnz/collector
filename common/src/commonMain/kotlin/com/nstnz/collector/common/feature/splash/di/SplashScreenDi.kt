package com.nstnz.collector.common.feature.splash.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.splash.presentation.SplashScreenViewModel
import org.kodein.di.*

internal val splashScreenDi = DI.Module(name = Routes.Splash.name) {
    bind<SplashScreenViewModel>() with multiton {
        SplashScreenViewModel(instance(), instance(), instance())
    }
}