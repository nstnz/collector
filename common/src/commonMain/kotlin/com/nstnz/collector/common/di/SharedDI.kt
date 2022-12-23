package com.nstnz.collector.common.di

import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.converter.di.converterScreenDi
import com.nstnz.collector.common.feature.core.di.coreDi
import com.nstnz.collector.common.feature.main.di.mainScreenDi
import com.nstnz.collector.common.feature.source.di.sourceScreenDi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object SharedDI {

    fun init() {
        println("Launch application")
    }

    internal val di = DI {
        bind<Router>() with singleton { Router() }

        import(coreDi)
        import(mainScreenDi)
        import(sourceScreenDi)
        import(converterScreenDi)
    }
}
