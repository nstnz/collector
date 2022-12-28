package com.nstnz.collector.common.basic.router

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo

internal class Router() {

    private lateinit var navigator: Navigator
    private var resultHandler by mutableStateOf<Any?>(null)

    fun init(navigator: Navigator) {
        this.navigator = navigator
    }

    fun navigateToMainScreen() {
        navigator.navigate(Routes.Main, NavOptions(popUpTo = PopUpTo.First(inclusive = true)))
    }

    fun navigateToWelcomeScreen() {
        navigator.navigate(Routes.Welcome, NavOptions(popUpTo = PopUpTo.First(inclusive = true)))
    }

    fun navigateToSourceScreen(sourceId: String?, clearBackEntry: Boolean = false) {
        if (clearBackEntry) {
            navigator.popBackStack()
        }
        navigator.navigate(Routes.Source, null, sourceId)
    }

    fun navigateToConverterScreen() {
        navigator.navigate(Routes.Converter, NavOptions(popUpTo = PopUpTo.First(inclusive = true)))
    }

    fun navigateToSettingsScreen() {
        navigator.navigate(Routes.Settings, NavOptions(popUpTo = PopUpTo.First(inclusive = true)))
    }

    fun navigateToAddSourceScreen() {
        navigator.navigate(Routes.AddSource)
    }

    fun navigateToAddCountScreen(sourceId: String?) {
        navigator.navigate(Routes.AddCount, null, sourceId)
    }

    fun navigateToEditSourceScreen(sourceId: String?) {
        navigator.navigate(Routes.EditSource, null, sourceId)
    }

    fun navigateToEditSourceFundScreen(sourceFundId: String?) {
        navigator.navigate(Routes.EditCount, null, sourceFundId)
    }

    fun navigateToCurrenciesScreen(multiCheck: Boolean, saveChanges: Boolean, currency: String?) {
        navigator.navigate(Routes.Currencies, null, multiCheck, saveChanges, currency)
    }

    fun back() {
        navigator.goBack()
    }

    fun backWithResult(result: Any?) {
        resultHandler = result
        navigator.goBack()
    }

    fun <T> getLastResult(): T? {
        return (resultHandler as? T).also {
            if (it != null) {
                resultHandler = null
            }
        }
    }
}