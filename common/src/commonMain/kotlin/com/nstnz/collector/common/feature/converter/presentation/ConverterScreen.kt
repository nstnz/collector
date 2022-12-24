package com.nstnz.collector.common.feature.converter.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.basic.texts.ConverterScreen_Title
import com.nstnz.collector.common.basic.texts.MainScreen_Title

@Composable
internal fun ConverterScreen(
    viewState: ConverterScreenState,
    onMainTabClick: () -> Unit = {},
    onSettingsTabClick: () -> Unit = {},
    onChangeCurrenciesClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            NavBarComponent(
                titleColor = AppTheme.colors.primaryText(),
                title = ConverterScreen_Title,
            )
        },
        bottomBar = {
            NavigationBarComponent(
                converterTabSelected = true,
                mainTabClick = onMainTabClick,
                converterTabClick = {},
                settingsTabClick = onSettingsTabClick
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Button(
                onClick = onChangeCurrenciesClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTheme.indents.x6)
                    .background(AppTheme.colors.backgroundError()),
                content = {
                    Text(
                        text = "To Currencies",
                        color = AppTheme.colors.primaryText(),
                        style = AppTheme.typography.headingMegaLarge
                    )
                }
            )
        }
    }
}