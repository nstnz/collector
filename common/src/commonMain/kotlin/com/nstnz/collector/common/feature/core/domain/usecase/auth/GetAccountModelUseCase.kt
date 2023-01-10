package com.nstnz.collector.common.feature.core.domain.usecase.auth

import com.nstnz.collector.common.feature.core.data.prefs.UserPrefs
import com.nstnz.collector.common.feature.core.domain.model.AccountModel

internal class GetAccountModelUseCase(
    private val userPrefs: UserPrefs
) {

    operator fun invoke(): AccountModel = AccountModel(
        userPrefs.googleAuthToken,
        userPrefs.email,
        userPrefs.name,
        userPrefs.photoUrl
    )
}