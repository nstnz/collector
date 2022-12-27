package com.nstnz.collector.common.feature.source.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.swipedismiss.SwipeDismissComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.title.TitleComponent
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.feature.core.domain.model.SourceCountDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceDomainModel
import com.nstnz.collector.common.feature.main.presentation.CurrenciesBlock

@Composable
internal fun SourceScreen(
    viewState: SourceScreenState,
    onAddCountClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onCountClick: (String) -> Unit = {},
    onDeleteCountClick: (String) -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            DefaultNavComponent(
                onBackClick,
                title = (viewState as? SourceScreenState.Default)?.sourceMainModel?.name.orEmpty()
            )
        }
    ) {
        if (viewState is SourceScreenState.Default) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                HintPanel(viewState.sourceMainModel)
                Row(
                    Modifier
                        .fillMaxSize()
                        .horizontalScroll(rememberScrollState())
                ) {
                    SpacerComponent { x3 }
                    CurrenciesBlock(viewState.sourceMainModel.favoriteSums, {})
                    SpacerComponent { x3 }
                }

                SpacerComponent { x3 }
                TitleComponent(
                    title = "Счета",
                    onAddClick = onAddCountClick
                )
                SpacerComponent { x1 }
                viewState.sourceMainModel.counts.forEach {
                    CountDetailedPanel(
                        count = it,
                        onCountClick = onCountClick,
                        onDeleteCountClick = onDeleteCountClick
                    )
                    SpacerComponent { x3 }
                }
                SpacerComponent { x3 }
            }
        }
    }
}

@Composable
private fun HintPanel(
    sourceMainModel: SourceDomainModel
) {
    Column(
        Modifier
            .padding(AppTheme.indents.x3)
            .fillMaxWidth()
            .background(
                if (sourceMainModel.originalCurrency.crypto) {
                    AppTheme.colors.accent2Color()
                } else {
                    AppTheme.colors.accentColor()
                },
                shape = AppTheme.shapes.x3
            )
            .padding(AppTheme.indents.x3)
    ) {
        Text(
            text = sourceMainModel.originalFormattedSum,
            color = AppTheme.colors.primaryText(),
            style = AppTheme.typography.headingMegaLarge
        )
        SpacerComponent { x0_5 }
        Text(
            text = sourceMainModel.originalSum.currency.name,
            color = AppTheme.colors.secondaryText(),
            style = AppTheme.typography.bodySmall
        )
    }
}

@Composable
private fun CountDetailedPanel(
    count: SourceCountDomainModel,
    onCountClick: (String) -> Unit = {},
    onDeleteCountClick: (String) -> Unit = {},
) {
    SwipeDismissComponent(
        modifier = Modifier.fillMaxWidth(),
        onDeleteClick = {
            onDeleteCountClick(count.id)
        }
    ) {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x3)
                .clickable { onCountClick(count.id) }) {
            Box(
                Modifier.size(AppTheme.indents.x6)
                    .background(AppTheme.colors.backgroundSecondary(), AppTheme.shapes.x2)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = count.originalSum.currency.code.uppercase(),
                    color = if (count.originalSum.currency.crypto) {
                        AppTheme.colors.accent2Color()
                    } else {
                        AppTheme.colors.accentColor()
                    },
                    style = AppTheme.typography.headingMedium
                )
            }
            SpacerComponent { x2 }
            Column(Modifier.weight(1f)) {
                Text(
                    text = count.originalFormattedSum,
                    color = AppTheme.colors.primaryBackgroundText(),
                    style = AppTheme.typography.headingMedium
                )
                SpacerComponent { x0_5 }
                count.favoriteSums.forEach {
                    Text(
                        text = it.formattedSum,
                        color = AppTheme.colors.secondaryBackgroundText(),
                        style = AppTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}