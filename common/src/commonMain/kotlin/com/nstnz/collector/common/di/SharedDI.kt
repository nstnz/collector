package com.nstnz.collector.common.di

import com.nstnz.collector.common.feature.auth.domain.UseCase2
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object SharedDI {

    fun init() {
        println("Launch application")
    }

    // USAGE: val loginUseCase by SharedDI.kodeinInjector.instance<UseCase>()

    internal val kodeinInjector = DI {
        bind<UseCase2>() with provider { UseCase2() }
    }
}
