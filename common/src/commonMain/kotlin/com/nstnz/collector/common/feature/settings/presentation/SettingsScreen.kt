package com.nstnz.collector.common.feature.settings.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.theme.*

@Composable
internal fun SettingsScreen(
    viewState: SettingsScreenState,
    onConverterTabCLick: () -> Unit = {},
    onMainTabClick: () -> Unit = {},
) {
    GradientScaffold(
        bottomBar = {
            NavigationBarComponent(
                settingsTabSelected = true,
                settingsTabClick = {},
                converterTabClick = onConverterTabCLick,
                mainTabClick = onMainTabClick,
            )
        }
    ) {
        Text(
            text = "SETTINGS",
            color = AppTheme.colors.primaryText(),
            style = AppTheme.typography.headingMegaLarge
        )
    }
}