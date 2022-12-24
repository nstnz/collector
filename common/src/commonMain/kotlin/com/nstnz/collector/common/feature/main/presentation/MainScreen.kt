package com.nstnz.collector.common.feature.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.basic.texts.MainScreen_Title

@Composable
internal fun MainScreen(
    viewState: MainScreenState,
    onAddSourceClick: () -> Unit = {},
    onSourceClick: (String) -> Unit = {},
    onConverterTabCLick: () -> Unit = {},
    onSettingsTabClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            NavBarComponent(
                modifier = Modifier.background(AppTheme.colors.backgroundSheetPrimary()),
                titleColor = AppTheme.colors.primaryText(),
                title = MainScreen_Title,
                actions = {
                    IconButton(onClick = onAddSourceClick) {

                    }
                }
            )
        },
        bottomBar = {
            NavigationBarComponent(
                mainTabSelected = true,
                mainTabClick = {},
                converterTabClick = onConverterTabCLick,
                settingsTabClick = onSettingsTabClick,
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            MainResultPanel()
            SpacerComponent { x4 }
            for (i in 0 until 10) {
                SourceDetailedPanel(
                    sourceName = i.toString(),
                    total = "$123456",
                    onSourceClick = onSourceClick
                )
                SpacerComponent { x2 }
            }

            SpacerComponent { x4 }
        }
    }
}

@Composable
private fun MainResultPanel() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(
                AppTheme.colors.backgroundSheetPrimary(),
                shape = AppTheme.shapes.x4_5_bottom
            )
            .padding(AppTheme.indents.x3)
    ) {
        Text(
            text = "123.34$",
            color = AppTheme.colors.primaryText(),
            style = AppTheme.typography.headingMegaLarge
        )
        SpacerComponent { x0_5 }
        Text(
            text = "Hello",
            color = AppTheme.colors.primaryText(),
            style = AppTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun SourceDetailedPanel(
    sourceName: String,
    total: String,
    onSourceClick: (String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.indents.x3)
            .background(
                AppTheme.colors.backgroundSheetSecondary(),
                shape = AppTheme.shapes.x2
            )
            .padding(AppTheme.indents.x3)
            .noEffectsClickable { onSourceClick(sourceName) }
    ) {
        Column(
            Modifier
                .weight(1f)
        ) {
            Text(
                text = total,
                color = AppTheme.colors.secondaryBackgroundText(),
                style = AppTheme.typography.headingXlarge
            )
            SpacerComponent { x0_5 }
            Text(
                text = sourceName,
                color = AppTheme.colors.secondaryBackgroundText(),
                style = AppTheme.typography.bodyMedium
            )
        }
        Text(
            text = ">",
            color = AppTheme.colors.secondaryBackgroundText(),
            style = AppTheme.typography.headingXlarge
        )
    }
}