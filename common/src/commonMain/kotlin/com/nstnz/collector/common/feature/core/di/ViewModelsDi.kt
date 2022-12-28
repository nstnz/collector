package com.nstnz.collector.common.feature.core.di

import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenViewModel
import com.nstnz.collector.common.feature.addcount.presentation.AddCountViewModelParams
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenViewModel
import com.nstnz.collector.common.feature.converter.presentation.ConverterScreenViewModel
import com.nstnz.collector.common.feature.currencies.presentation.CurrenciesScreenViewModel
import com.nstnz.collector.common.feature.currencies.presentation.CurrenciesViewModelParams
import com.nstnz.collector.common.feature.editcount.presentation.EditCountScreenViewModel
import com.nstnz.collector.common.feature.editsource.presentation.EditSourceScreenViewModel
import com.nstnz.collector.common.feature.main.presentation.MainScreenViewModel
import com.nstnz.collector.common.feature.settings.presentation.SettingsScreenViewModel
import com.nstnz.collector.common.feature.source.presentation.SourceScreenViewModel
import com.nstnz.collector.common.feature.splash.presentation.SplashScreenViewModel
import com.nstnz.collector.common.feature.welcome.presentation.WelcomeScreenViewModel
import org.kodein.di.*
import org.kodein.di.bindings.UnboundedScope

internal val editSourceScope = object : UnboundedScope() {}
internal val editCountScope = object : UnboundedScope() {}
internal val sourceScope = object : UnboundedScope() {}
internal val mainScope = object : UnboundedScope() {}
internal val addSourceScope = object : UnboundedScope() {}
internal val currenciesScope = object : UnboundedScope() {}
internal val addCountScope = object : UnboundedScope() {}
internal val converterScope = object : UnboundedScope() {}
internal val splashScope = object : UnboundedScope() {}
internal val welcomeScope = object : UnboundedScope() {}
internal val settingsScope = object : UnboundedScope() {}

internal val viewModelsDi = DI.Module(name = "ViewModels") {
    bind {
        scoped(currenciesScope).multiton { params: CurrenciesViewModelParams ->
            CurrenciesScreenViewModel(
                params,
                instance(),
                instance(),
                instance()
            )
        }
    }
    bind {
        scoped(addCountScope).multiton { params: AddCountViewModelParams ->
            AddCountScreenViewModel(
                params.sourceId,
                instance(),
                instance(),
                instance(),
                instance(),
            )
        }
    }
    bind {
        scoped(addSourceScope).singleton {
            AddSourceScreenViewModel(
                instance(),
                instance(),
                instance(),
            )
        }
    }
    bind<ConverterScreenViewModel>() with scoped(converterScope).singleton {
        ConverterScreenViewModel(instance(), instance(), instance(), instance())
    }
    bind<SplashScreenViewModel>() with scoped(splashScope).singleton {
        SplashScreenViewModel(instance(), instance(), instance(), instance())
    }
    bind<SettingsScreenViewModel>() with scoped(settingsScope).singleton {
        SettingsScreenViewModel(instance(), instance(), instance())
    }
    bind<WelcomeScreenViewModel>() with scoped(welcomeScope).singleton {
        WelcomeScreenViewModel(instance(), instance(), instance())
    }
    bind<SourceScreenViewModel>() with scoped(sourceScope).multiton { sourceId: String ->
        SourceScreenViewModel(
            sourceId,
            instance(),
            instance(),
            instance(),
        )
    }
    bind<EditCountScreenViewModel>() with scoped(editCountScope).multiton { sourceFundId: String ->
        EditCountScreenViewModel(
            sourceFundId,
            instance(),
            instance(),
            instance(),
        )
    }
    bind<MainScreenViewModel>() with scoped(mainScope).multiton {
        MainScreenViewModel(
            instance(),
            instance(),
            instance(),
        )
    }
    bind() {
        scoped(editSourceScope).multiton { sourceId: String ->
            EditSourceScreenViewModel(
                sourceId,
                instance(),
                instance(),
                instance(),
                instance(),
            )
        }
    }
}