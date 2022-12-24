package com.nstnz.collector.common.feature.settings.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.settings.presentation.SettingsScreenViewModel
import org.kodein.di.*

internal val settingsScreenDi = DI.Module(name = Routes.Settings.name) {
    bind<SettingsScreenViewModel>() with multiton {
        SettingsScreenViewModel(instance())
    }
}