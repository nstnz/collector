package com.nstnz.collector.common.basic.router

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo

internal class Router() {

    private lateinit var navigator: Navigator

    fun init(navigator: Navigator) {
        this.navigator = navigator
    }

    fun navigateToMainScreen() {
        navigator.navigate(Routes.Main, NavOptions(popUpTo = PopUpTo.First(inclusive = true)))
    }

    fun navigateToSourceScreen(sourceId: String) {
        navigator.navigate(Routes.Source, null, sourceId)
    }

    fun navigateToConverterScreen() {
        navigator.navigate(Routes.Converter)
    }

    fun navigateToSettingsScreen() {
        navigator.navigate(Routes.Settings)
    }

    fun navigateToAddSourceScreen() {
        navigator.navigate(Routes.AddSource)
    }

    fun navigateToAddCountScreen(sourceId: String?) {
        navigator.navigate(Routes.AddCount, null, sourceId)
    }

    fun navigateToCurrenciesScreen(multiCheck: Boolean, saveChanges: Boolean) {
        navigator.navigate(Routes.Currencies, null, multiCheck, saveChanges)
    }

    fun back() {
        navigator.goBack()
    }
}