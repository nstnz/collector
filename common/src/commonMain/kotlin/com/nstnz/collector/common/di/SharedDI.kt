package com.nstnz.collector.common.di

import com.nstnz.collector.common.feature.auth.domain.UseCase2
import com.nstnz.collector.common.feature.main.presentation.MainScreenViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object SharedDI {

    fun init() {
        println("Launch application")
    }

    internal val di = DI {
        bind<UseCase2>() with provider { UseCase2() }

        bind<MainScreenViewModel>() with provider {
            MainScreenViewModel(instance())
        }
    }
}
