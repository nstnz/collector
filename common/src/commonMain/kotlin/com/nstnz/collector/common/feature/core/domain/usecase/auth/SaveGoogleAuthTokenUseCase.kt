package com.nstnz.collector.common.feature.core.domain.usecase.auth

import com.nstnz.collector.common.feature.core.data.prefs.UserPrefs
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import com.nstnz.collector.common.randomUUID
import io.ktor.util.date.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class SaveGoogleAuthTokenUseCase(
    private val userPrefs: UserPrefs
) {

    operator fun invoke(
        token: String?,
        email: String? = null,
        name: String? = null,
        photoUrl: String? = null
    ) {
        userPrefs.googleAuthToken = token
        userPrefs.email = email
        userPrefs.name = name
        userPrefs.photoUrl = photoUrl
    }
}