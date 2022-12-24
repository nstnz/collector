package com.nstnz.collector.common.feature.splash.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.splash.presentation.SplashScreenViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal val splashScreenDi = DI.Module(name = Routes.Splash.name) {
    bind<SplashScreenViewModel>() with provider {
        SplashScreenViewModel(instance(), instance(), instance())
    }
}