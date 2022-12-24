package com.nstnz.collector.common.feature.settings.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.settings.presentation.SettingsScreenViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal val settingsScreenDi = DI.Module(name = Routes.Settings.name) {
    bind<SettingsScreenViewModel>() with provider {
        SettingsScreenViewModel(instance())
    }
}