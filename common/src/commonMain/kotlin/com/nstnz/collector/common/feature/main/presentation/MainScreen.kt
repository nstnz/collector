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
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nstnz.collector.common.basic.texts.MainScreen_AddSource
import com.nstnz.collector.common.basic.texts.MainScreen_NoSources
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.basic.texts.MainScreen_Title
import com.nstnz.collector.common.basic.texts.MainScreen_TotalSum
import com.nstnz.collector.common.design.button.PrimaryButtonComponent
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.feature.main.domain.model.SourceMainModel

@Composable
internal fun MainScreen(
    viewState: MainScreenState,
    onAddCount: () -> Unit = {},
    onAddSource: () -> Unit = {},
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
            is MainScreenState.Default -> MainScreenStateDefault(
                viewState,
                onAddSource,
                onSourceClick
            )
            MainScreenState.Loading -> {}
        }
    }
}

@Composable
private fun MainScreenStateDefault(
    viewState: MainScreenState.Default,
    onAddSource: () -> Unit = {},
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
            if (viewState.sourcesMainModel.sources.isNotEmpty()) {
                viewState.sourcesMainModel.sources.forEach {
                    SourceDetailedPanel(
                        source = it,
                        onSourceClick = onSourceClick
                    )
                    SpacerComponent { x2 }
                }
            } else {
                EmptySourcesComponent(onAddSource)
            }
            SpacerComponent { x4 }
        }
    }
}

@Composable
private fun EmptySourcesComponent(
    onAddCount: () -> Unit = {},
) {
    Column(
        Modifier.fillMaxSize().padding(horizontal = AppTheme.indents.x4),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacerComponent { x8 }
        Text(
            text = MainScreen_NoSources,
            color = AppTheme.colors.primaryBackgroundText(),
            style = AppTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        SpacerComponent { x2 }
        PrimaryButtonComponent(
            text = MainScreen_AddSource,
            onClick = onAddCount
        )
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
    source: SourceMainModel,
    onSourceClick: (String) -> Unit
) {
    CardComponent(
        Modifier
            .padding(horizontal = AppTheme.indents.x3)
            .fillMaxWidth(),
        shape = AppTheme.shapes.x2,
        elevation = AppTheme.elevations.secondaryCard,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(
                    bottom = AppTheme.indents.x3,
                    top = AppTheme.indents.x3,
                    start = AppTheme.indents.x3,
                    end = AppTheme.indents.x2
                )
                .noEffectsClickable { onSourceClick(source.id) }
        ) {
            Row {
                Column(
                    Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = source.sum.toString(),
                        color = AppTheme.colors.secondaryBackgroundText(),
                        style = AppTheme.typography.headingXlarge
                    )
                    SpacerComponent { x0_5 }
                    Text(
                        text = source.name,
                        color = AppTheme.colors.secondaryBackgroundText(),
                        style = AppTheme.typography.bodyMedium
                    )
                }
                Icon(
                    Icons.Rounded.NavigateNext,
                    null,
                    modifier = Modifier.padding(top = AppTheme.indents.x0_5)
                        .size(AppTheme.indents.x3),
                    tint = AppTheme.colors.hintBackgroundText()
                )
            }
            SpacerComponent { x2 }
            Box(
                Modifier.fillMaxWidth().height(AppTheme.indents.x0_125)
                    .background(AppTheme.colors.overlayColor())
            )
            SpacerComponent { x1 }
            source.funds.forEach {
                Text(
                    text = it.sum.toString(),
                    color = AppTheme.colors.hintBackgroundText(),
                    style = AppTheme.typography.bodyMedium
                )
            }
        }
    }
}