package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.main.presentation.MainScreenState
import com.nstnz.collector.common.feature.splash.presentation.SplashScreen
import com.nstnz.collector.common.feature.splash.presentation.SplashScreenState
import com.nstnz.collector.common.feature.welcome.presentation.WelcomeScreen
import com.nstnz.collector.common.feature.welcome.presentation.WelcomeScreenState

@Composable
@Preview
private fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreen(
            WelcomeScreenState(
                "USD",
                "",
                listOf(
                    getMockCurrencyDomainModel("AED"),
                    getMockCurrencyDomainModel("USD"),
                    getMockCurrencyDomainModel("DTC"),
                    getMockCurrencyDomainModel("EUR"),
                    getMockCurrencyDomainModel("AED"),
                ),
                listOf(
                    getMockCurrencyDomainModel("AED"),
                    getMockCurrencyDomainModel("USD"),
                    getMockCurrencyDomainModel("DTC"),
                    getMockCurrencyDomainModel("EUR"),
                    getMockCurrencyDomainModel("AED"),
                ),
            )
        )
    }
}