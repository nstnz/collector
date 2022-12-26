package com.nstnz.collector.common.feature.source.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.feature.main.domain.model.SourceFundMainModel
import com.nstnz.collector.common.feature.main.domain.model.SourceMainModel

@Composable
internal fun SourceScreen(
    viewState: SourceScreenState,
    onAddCountClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            NavBarComponent(
                modifier = Modifier.background(AppTheme.colors.backgroundPrimary()),
                title = "",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Rounded.ArrowBackIosNew,
                            null,
                            modifier = Modifier.size(AppTheme.indents.x3_5),
                            tint = AppTheme.colors.primaryBackgroundText()
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            Icons.Rounded.Edit,
                            null,
                            modifier = Modifier.size(AppTheme.indents.x4_5),
                            tint = AppTheme.colors.primaryBackgroundText()
                        )
                    }
                    IconButton(onClick = onAddCountClick) {
                        Icon(
                            Icons.Rounded.Add,
                            null,
                            modifier = Modifier.size(AppTheme.indents.x4_5),
                            tint = AppTheme.colors.primaryBackgroundText()
                        )
                    }
                }
            )
        }
    ) {
        if (viewState is SourceScreenState.Default) {
            Column(
                Modifier
                    .fillMaxSize()
            ) {
                HintPanel(
                    viewState.sourceMainModel.formattedSum,
                    viewState.sourceMainModel.name,
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        bottom = AppTheme.indents.x3,
                        start = AppTheme.indents.x3,
                        top = AppTheme.indents.x3,
                        end = AppTheme.indents.x3,
                    )
                ) {
                    items(count = viewState.sourceMainModel.funds.size) { index ->
                        val fund = viewState.sourceMainModel.funds[index]
                        SourceFundCell(
                            fund
                        )
                        SpacerComponent { x2 }
                    }
                }
            }
        }
    }
}

@Composable
private fun HintPanel(
    total: String,
    title: String,
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
                text = title,
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
private fun SourceFundCell(
    fund: SourceFundMainModel,
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
                text = fund.formattedOriginalSum,
                color = AppTheme.colors.secondaryBackgroundText(),
                style = AppTheme.typography.headingXlarge
            )
            SpacerComponent { x1 }
            Text(
                text = fund.formattedSum,
                color = AppTheme.colors.secondaryBackgroundText(),
                style = AppTheme.typography.bodyMedium
            )
        }
    }
}