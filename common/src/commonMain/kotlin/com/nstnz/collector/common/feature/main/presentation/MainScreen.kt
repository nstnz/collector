package com.nstnz.collector.common.feature.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.basic.texts.MainScreen_Title
import com.nstnz.collector.common.basic.texts.MainScreen_TotalSum
import com.nstnz.collector.common.design.card.CardComponent

@Composable
internal fun MainScreen(
    viewState: MainScreenState,
    onAddCount: () -> Unit = {},
    onSourceClick: (String) -> Unit = {},
    onConverterTabCLick: () -> Unit = {},
    onSettingsTabClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            NavBarComponent(
                modifier = Modifier.background(AppTheme.colors.backgroundPrimary()),
                title = "",
                actions = {
                    IconButton(onClick = onAddCount) {
                        Icon(
                            Icons.Rounded.Add,
                            null,
                            modifier = Modifier.size(AppTheme.indents.x4_5),
                            tint = AppTheme.colors.primaryBackgroundText()
                        )
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
        when (viewState) {
            is MainScreenState.Default -> MainScreenStateDefault(viewState, onSourceClick)
            MainScreenState.Loading -> {}
        }
    }
}

@Composable
private fun MainScreenStateDefault(
    viewState: MainScreenState.Default,
    onSourceClick: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        MainResultPanel(viewState.sourcesMainModel.sum.toString())
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
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
private fun MainResultPanel(
    total: String
) {
    CardComponent {
        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    AppTheme.colors.backgroundPrimary(),
                    shape = AppTheme.shapes.x4_5_bottom
                )
                .padding(AppTheme.indents.x3)
        ) {
            Text(
                text = MainScreen_TotalSum,
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.bodyMedium
            )
            SpacerComponent { x0_5 }
            Text(
                text = total,
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMegaLarge
            )
        }

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
                AppTheme.colors.backgroundSecondary(),
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