package com.nstnz.collector.common

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.basic.router.*
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenHolder
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenHolder
import com.nstnz.collector.common.feature.converter.presentation.ConverterScreenHolder
import com.nstnz.collector.common.feature.currencies.presentation.CurrenciesScreenHolder
import com.nstnz.collector.common.feature.listsource.presentation.ListSourceScreenHolder
import com.nstnz.collector.common.feature.main.presentation.MainScreenHolder
import com.nstnz.collector.common.feature.settings.presentation.SettingsScreenHolder
import com.nstnz.collector.common.feature.source.presentation.SourceScreenHolder
import com.nstnz.collector.common.feature.splash.presentation.SplashScreenHolder
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
        NavHost(
            navigator = navigator,
            initialRoute = Routes.Splash.name,
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
                        Routes.Source -> SourceScreenHolder(arg1.orEmpty())
                        Routes.Settings -> SettingsScreenHolder()
                        Routes.Converter -> ConverterScreenHolder()
                        Routes.Currencies -> CurrenciesScreenHolder(
                            arg1.toBoolean(), arg2.toBoolean(), arg3.orEmpty()
                        )
                        Routes.Splash -> SplashScreenHolder()
                        Routes.AddSource -> AddSourceScreenHolder()
                        Routes.AddCount -> AddCountScreenHolder(arg1.orEmpty(), arg2.orEmpty())
                        Routes.ListSource -> ListSourceScreenHolder(arg1.orEmpty(), arg2.orEmpty())
                    }
                }
            }
        }
    }
}