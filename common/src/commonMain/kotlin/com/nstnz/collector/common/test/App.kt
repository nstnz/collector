package com.nstnz.collector.common.test

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.basic.router.*
import com.nstnz.collector.common.basic.router.Arg1
import com.nstnz.collector.common.basic.router.Arg2
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.di.SharedDI
import com.nstnz.collector.common.feature.converter.presentation.ConverterScreenHolder
import com.nstnz.collector.common.feature.main.presentation.MainScreenHolder
import com.nstnz.collector.common.feature.source.presentation.SourceScreenHolder
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.kodein.di.instance

@Composable
internal fun App() {
    val navigator = rememberNavigator()
    val router: Router by SharedDI.di.instance()
    router.init(navigator)

    Box(Modifier.fillMaxSize()) {
        NavHost(navigator = navigator,
            initialRoute = Routes.Main.name,
            navTransition = NavTransition(
                createTransition = EnterTransition.None,
                resumeTransition = EnterTransition.None,
                destroyTransition = ExitTransition.None,
                pauseTransition = ExitTransition.None,
            )
        ) {
            Routes.values().forEach { route ->
                scene(route.name) {
                    val arg1 = it.queryString?.map?.get(Arg1)?.firstOrNull()
                    val arg2 = it.queryString?.map?.get(Arg2)?.firstOrNull()
                    val arg3 = it.queryString?.map?.get(Arg3)?.firstOrNull()

                    when (route) {
                        Routes.Main -> MainScreenHolder()
                        Routes.Source -> SourceScreenHolder(arg1.toString())
                        Routes.Settings -> TODO()
                        Routes.Converter -> ConverterScreenHolder()
                    }
                }
            }
        }
    }
}