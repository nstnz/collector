package com.nstnz.collector.common.feature.currencies.data.prefs

import de.galdigital.preferences.SharedPreferences
import de.galdigital.preferences.preference

internal class CurrenciesPrefs(sharedPreferences: SharedPreferences) {

    var defaultCurrencyCode: String?
            by preference(sharedPreferences, "defaultCurrencyCode", "")
}