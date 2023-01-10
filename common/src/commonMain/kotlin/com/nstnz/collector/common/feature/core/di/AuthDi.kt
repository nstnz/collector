package com.nstnz.collector.common.feature.core.di

import com.nstnz.collector.common.feature.core.data.prefs.UserPrefs
import com.nstnz.collector.common.feature.core.domain.usecase.auth.GetAccountModelUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.auth.SaveGoogleAuthTokenUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal val authDi = DI.Module(name = "Auth") {
    bind<UserPrefs>() with provider {
        UserPrefs(
            instance(),
        )
    }
    bind<SaveGoogleAuthTokenUseCase>() with provider {
        SaveGoogleAuthTokenUseCase(
            instance(),
        )
    }
    bind<GetAccountModelUseCase>() with provider {
        GetAccountModelUseCase(
            instance(),
        )
    }
}