package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.main.presentation.MainScreenState
import com.nstnz.collector.common.feature.settings.presentation.SettingsScreen
import com.nstnz.collector.common.feature.settings.presentation.SettingsScreenState
import com.nstnz.collector.common.feature.splash.presentation.SplashScreen
import com.nstnz.collector.common.feature.splash.presentation.SplashScreenState

@Composable
@Preview
private fun SettingsScreenPreview() {
    AppTheme {
        SettingsScreen(
            SettingsScreenState(
                currency = getMockCurrencyDomainModel("HJshd"),
                favoriteCurrencies = emptyList()
            )
        )
    }
}