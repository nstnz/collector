package com.nstnz.collector.common.feature.settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.basic.texts.MainScreen_Title
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent

@Composable
internal fun SettingsScreen(
    viewState: SettingsScreenState,
    onConverterTabCLick: () -> Unit = {},
    onMainTabClick: () -> Unit = {},
    onChangeCurrencyClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            DefaultNavComponent(
                title = "Настройки",
                showBackButton = false
            )
        },
        bottomBar = {
            NavigationBarComponent(
                settingsTabSelected = true,
                settingsTabClick = {},
                converterTabClick = onConverterTabCLick,
                mainTabClick = onMainTabClick,
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SpacerComponent { x3 }
            TextSelectorComponent(
                modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
                label = "Валюта по умолчанию",
                hint = "Валюта по умолчанию в которой будут создаваться все счета и тд и тп",
                text = viewState.currency?.code.orEmpty(),
                onClick = {
                    onChangeCurrencyClick()
                }
            )
            SpacerComponent { x3 }
        }
    }
}