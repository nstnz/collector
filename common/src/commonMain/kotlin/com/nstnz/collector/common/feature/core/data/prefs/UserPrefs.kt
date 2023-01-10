package com.nstnz.collector.common.feature.core.data.prefs

import de.galdigital.preferences.SharedPreferences
import de.galdigital.preferences.preference

internal class UserPrefs(sharedPreferences: SharedPreferences) {

    var googleAuthToken: String?
            by preference(sharedPreferences, "googleAuthToken", null)

    var email: String?
            by preference(sharedPreferences, "email", null)

    var name: String?
            by preference(sharedPreferences, "name", null)

    var photoUrl: String?
            by preference(sharedPreferences, "photoUrl", null)
}