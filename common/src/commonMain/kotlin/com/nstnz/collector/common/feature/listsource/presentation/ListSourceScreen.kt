package com.nstnz.collector.common.feature.listsource.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.basic.texts.MainScreen_TotalSum
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.feature.source.domain.model.SourceFundModel
import com.nstnz.collector.common.feature.source.domain.model.SourceModel

@Composable
internal fun ListSourceScreen(
    viewState: ListSourceScreenState,
    onBackClick: () -> Unit = {},
    onSelect: (source: SourceModel, fund: SourceFundModel) -> Unit = { _, _ -> },
) {
    GradientScaffold(
        topBar = { DefaultNavComponent(onBackClick) },
    ) {
        if (viewState is ListSourceScreenState.Default) {
            Column(
                Modifier
                    .fillMaxSize()
            ) {
                HintPanel()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        bottom = AppTheme.indents.x3,
                        start = AppTheme.indents.x3,
                        top = AppTheme.indents.x3,
                        end = AppTheme.indents.x3,
                    )
                ) {
                    items(count = viewState.sources.size) { index ->
                        val source = viewState.sources[index]
                        SourceCell(
                            source,
                            viewState.selectedSourceId,
                            viewState.selectedSourceFundId,
                            onSelect
                        )
                        SpacerComponent { x2 }
                    }
                }
            }
        }
    }
}

@Composable
private fun HintPanel() {
    CardComponent {
        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    AppTheme.colors.backgroundPrimary(),
                    shape = AppTheme.shapes.x4_5_bottom
                )
                .padding(horizontal = AppTheme.indents.x3)
        ) {
            SpacerComponent { x3 }
            Text(
                text = "Choose source and fund:",
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMedium
            )
            SpacerComponent { x0_5 }
            Text(
                text = "JHKdhkjahdkjshd as dkhjsd klfsdkfsdfhlsdklsf hasd",
                color = AppTheme.colors.secondaryBackgroundText(),
                style = AppTheme.typography.bodyMedium
            )
            SpacerComponent { x3 }
        }
    }
}

@Composable
private fun SourceCell(
    source: SourceModel,
    selectedSourceId: String,
    selectedSourceFundId: String,
    onSelect: (source: SourceModel, fund: SourceFundModel) -> Unit
) {
    CardComponent(
        Modifier
            .fillMaxWidth(),
        shape = AppTheme.shapes.x2,
        elevation = AppTheme.elevations.secondaryCard,
    ) {
        Column(
            Modifier.fillMaxWidth()
                .padding(horizontal = AppTheme.indents.x3, vertical = AppTheme.indents.x2)
        ) {
            Text(
                text = source.name,
                color = if (selectedSourceId == source.id)
                    AppTheme.colors.accentColor() else AppTheme.colors.secondaryBackgroundText(),
                style = AppTheme.typography.headingXlarge
            )

            source.funds.forEachIndexed { index, fund ->
                Column(Modifier.fillMaxWidth().clickable {
                    onSelect(source, fund)
                }) {
                    SpacerComponent { x2 }
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = fund.name,
                            color = if (selectedSourceFundId == fund.id)
                                AppTheme.colors.accentColor() else AppTheme.colors.hintBackgroundText(),
                            style = AppTheme.typography.bodyMedium
                        )
                        Icon(
                            Icons.Rounded.NavigateNext,
                            null,
                            modifier = Modifier.size(AppTheme.indents.x3),
                            tint = AppTheme.colors.hintBackgroundText()
                        )
                    }
                    if (index != source.funds.lastIndex) {
                        SpacerComponent { x2 }
                        Box(
                            Modifier.fillMaxWidth().height(AppTheme.indents.x0_125)
                                .background(AppTheme.colors.overlayColor())
                        )
                    }
                }
            }
        }
    }
}