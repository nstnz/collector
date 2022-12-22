package com.nstnz.collector.common.test

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.main.presentation.MainScreenHolder
import com.nstnz.collector.common.feature.source.presentation.SourceScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
internal fun App() {
    val navigator = rememberNavigator()
    Box(Modifier.fillMaxSize()) {
        NavHost(navigator = navigator, initialRoute = Routes.Main.name) {
            Routes.values().forEach { route ->
                scene(route.name) {
                    when (route) {
                        Routes.Main -> MainScreenHolder()
                        Routes.Source -> SourceScreen("")
                        Routes.Settings -> TODO()
                    }
                }
            }
        }
    }
}

private enum class Routes {
    Main,
    Source,
    Settings,
}