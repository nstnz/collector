package com.nstnz.collector.common.feature.source.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.texts.MainScreen_Title

@Composable
internal fun SourceScreen(
    sourceName: String,
    onAddSourceClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            NavBarComponent(
                modifier = Modifier.background(AppTheme.colors.backgroundSheetPrimary()),
                titleColor = AppTheme.colors.primaryText(),
                title = sourceName,
                actions = {
                    IconButton(onClick = onAddSourceClick) {

                    }
                }
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
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.indents.x3)
            .background(
                AppTheme.colors.backgroundSheetSecondary(),
                shape = AppTheme.shapes.x2
            )
            .padding(AppTheme.indents.x3)
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
}