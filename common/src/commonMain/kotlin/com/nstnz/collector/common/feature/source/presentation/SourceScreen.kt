package com.nstnz.collector.common.feature.source.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.nstnz.collector.common.basic.di.strings
import com.nstnz.collector.common.design.emptystate.EmptyStateComponent
import com.nstnz.collector.common.design.graph.GraphComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.swipedismiss.SwipeDismissComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.title.TitleComponent
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.feature.core.domain.model.SourceCountDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceDomainModel
import com.nstnz.collector.common.feature.main.presentation.CurrenciesBlock
import com.nstnz.collector.common.feature.main.presentation.SumResultPanel

@Composable
internal fun SourceScreen(
    viewState: SourceScreenState,
    onAddCountClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onCountClick: (String) -> Unit = {},
    onDeleteCountClick: (String) -> Unit = {},
    onAddCurrency: () -> Unit = {},
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
                HintPanel(viewState.sourceMainModel, onEditClick)
                BoxWithConstraints(Modifier.padding(horizontal = AppTheme.indents.x3)) {
                    GraphComponent(
                        points = viewState.points,
                        modifier = Modifier.fillMaxWidth(),
                        width = this.maxWidth,
                        text = "${viewState.sourceMainModel.originalCurrency.codeToShow} to ${viewState.favCode}"
                    )
                }
                SpacerComponent { x3 }
                CurrenciesBlock(viewState.sourceMainModel.favoriteSums, onAddCurrency)

                SpacerComponent { x3 }
                TitleComponent(
                    title = strings.Source_CountsTitle,
                    onAddClick = onAddCountClick
                )
                SpacerComponent { x1 }
                if (viewState.sourceMainModel.counts.isEmpty()) {
                    SpacerComponent { x2 }
                    EmptyStateComponent(
                        text = strings.Source_EmptyCountsTitle
                    )
                } else {
                    viewState.sourceMainModel.counts.forEach {
                        CountDetailedPanel(
                            count = it,
                            onCountClick = onCountClick,
                            onDeleteCountClick = onDeleteCountClick
                        )
                        SpacerComponent { x3 }
                    }
                }
                SpacerComponent { x3 }
            }
        }
    }
}

@Composable
private fun HintPanel(
    sourceMainModel: SourceDomainModel,
    onEditClick: () -> Unit
) {
    BoxWithConstraints(Modifier.fillMaxWidth().padding(vertical = AppTheme.indents.x3)) {
        val width = this.maxWidth - AppTheme.indents.x3 * 2
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
            SpacerComponent { x3 }
            SumResultPanel(
                name = sourceMainModel.originalCurrency.name,
                sum = sourceMainModel.originalFormattedSum,
                title = strings.Source_SumInDefault,
                modifier = Modifier.width(width)
                    .background(
                        AppTheme.colors.accentColor(),
                        shape = AppTheme.shapes.x3
                    ),
                onEditClick = onEditClick
            )
            SpacerComponent { x2 }
            SumResultPanel(
                name = sourceMainModel.defaultSum.currency.name,
                sum = sourceMainModel.defaultSum.formattedSum,
                title = strings.MainScreen_SumInDefault,
                modifier = Modifier.width(width)
                    .background(
                        AppTheme.colors.primaryBackgroundText(),
                        shape = AppTheme.shapes.x3
                    ),
                onEditClick = null
            )
            SpacerComponent { x3 }
        }
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
                    text = count.originalSum.currency.codeToShow,
                    color = if (count.originalSum.currency.crypto) {
                        AppTheme.colors.accent2Color()
                    } else {
                        AppTheme.colors.accentColor()
                    },
                    style = AppTheme.typography.headingMedium
                )
            }
            SpacerComponent { x2 }
            Column(Modifier.weight(1f).align(Alignment.CenterVertically)) {
                Text(
                    text = count.originalFormattedSum,
                    color = AppTheme.colors.primaryBackgroundText(),
                    style = AppTheme.typography.headingMedium
                )
            }
        }
    }
}