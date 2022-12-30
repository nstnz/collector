package com.nstnz.collector.common.feature.main.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.basic.texts.MainScreen_Title
import com.nstnz.collector.common.design.emptystate.EmptyStateComponent
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.swipedismiss.SwipeDismissComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.title.TitleComponent
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.feature.core.domain.model.CurrencySumDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceDomainModel

@Composable
internal fun MainScreen(
    viewState: MainScreenState,
    onAddCurrency: () -> Unit = {},
    onAddSource: () -> Unit = {},
    onSourceClick: (String) -> Unit = {},
    onConverterTabCLick: () -> Unit = {},
    onSettingsTabClick: () -> Unit = {},
    onDeleteSourceClick: (String) -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            DefaultNavComponent(
                title = MainScreen_Title,
                showBackButton = false
            )
        },
        bottomBar = {
            NavigationBarComponent(
                mainTabSelected = true,
                mainTabClick = {},
                converterTabClick = onConverterTabCLick,
                settingsTabClick = onSettingsTabClick,
            )
        },
    ) {
        when (viewState) {
            is MainScreenState.Default -> MainScreenStateDefault(
                viewState,
                onAddSource,
                onSourceClick,
                onDeleteSourceClick,
                onAddCurrency
            )
            MainScreenState.Loading -> {}
        }
    }
}

@Composable
private fun MainScreenStateDefault(
    viewState: MainScreenState.Default,
    onAddSource: () -> Unit = {},
    onSourceClick: (String) -> Unit,
    onDeleteSourceClick: (String) -> Unit = {},
    onAddCurrency: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SumResultPanel(
            name = viewState.sourcesMainModel.favoriteCurrencies.first().name,
            sum = viewState.sourcesMainModel.originalFormattedSum,
            title = "Сумма в валюте по умолчанию",
            onEditClick = null
        )
        CurrenciesBlock(viewState.sourcesMainModel.favoriteSums, onAddCurrency)

        SpacerComponent { x3 }
        TitleComponent(
            title = "Аккаунты",
            onAddClick = onAddSource
        )
        SpacerComponent { x1 }
        if (viewState.sourcesMainModel.sources.isEmpty()) {
            SpacerComponent { x2 }
            EmptyStateComponent(
                text = "Добавляйте свой первый аккаунт скорее скорее"
            )
        } else {
            viewState.sourcesMainModel.sources.forEach {
                SourceDetailedPanel(
                    source = it,
                    onSourceClick = onSourceClick,
                    onDeleteSourceClick = onDeleteSourceClick
                )
                SpacerComponent { x3 }
            }
        }
        SpacerComponent { x3 }
    }
}

@Composable
internal fun CurrenciesBlock(
    favoriteSums: List<CurrencySumDomainModel>,
    onAddCurrency: () -> Unit = {}
) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "Сумма в избранных валютах",
            color = AppTheme.colors.primaryBackgroundText(),
            style = AppTheme.typography.headingMedium,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = AppTheme.indents.x3)
        )
        SpacerComponent { x2 }
        Row(
            Modifier
                .fillMaxSize()
                .horizontalScroll(rememberScrollState())
        ) {
            SpacerComponent { x3 }
            CurrencyMainBlock(favoriteSums, onAddCurrency)
            SpacerComponent { x3 }
        }
    }
}


@Composable
internal fun CurrencyMainBlock(
    favoriteSums: List<CurrencySumDomainModel>,
    onAddCurrency: () -> Unit = {}
) {
    favoriteSums.forEach {
        Column(
            Modifier.background(
                AppTheme.colors.backgroundSecondary(),
                AppTheme.shapes.x3
            ).padding(AppTheme.indents.x3)
                .widthIn(min = AppTheme.indents.x16, max = AppTheme.indents.x32)
        ) {
            Text(
                text = it.formattedSum,
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMedium,
                maxLines = 1
            )
            SpacerComponent { x0_5 }
            Text(
                text = it.currency.name,
                color = AppTheme.colors.secondaryBackgroundText(),
                style = AppTheme.typography.bodySmall,
                maxLines = 1
            )
        }
        SpacerComponent { x2 }
    }
    Column(
        Modifier.background(
            AppTheme.colors.backgroundPrimary(),
            AppTheme.shapes.x3
        ).clickable { onAddCurrency() }
            .dashedBorder(
                width = AppTheme.indents.x0_125,
                color = AppTheme.colors.secondaryBackgroundText(),
                shape = AppTheme.shapes.x2,
                off = AppTheme.indents.x0_5,
                on = AppTheme.indents.x0_5
            ).padding(AppTheme.indents.x3)
    ) {
        Icon(
            Icons.Rounded.AddCircleOutline,
            null,
            modifier = Modifier.size(AppTheme.indents.x2_5),
            tint = AppTheme.colors.primaryBackgroundText()
        )
        SpacerComponent { x0_5 }
        Text(
            text = "Добавить валюту",
            color = AppTheme.colors.secondaryBackgroundText(),
            style = AppTheme.typography.bodySmall,
            maxLines = 1
        )
    }
}

@Composable
internal fun SumResultPanel(
    title: String,
    sum: String,
    name: String,
    onEditClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
        .padding(AppTheme.indents.x3)
        .fillMaxWidth()
        .background(
            AppTheme.colors.primaryBackgroundText(),
            shape = AppTheme.shapes.x3
        )
) {
    Column(
        modifier.padding(AppTheme.indents.x3)
    ) {
        Row {
            Text(
                text = title,
                color = AppTheme.colors.secondaryText(),
                style = AppTheme.typography.headingMedium
            )
            onEditClick?.let {
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .size(AppTheme.indents.x3),
                ) {
                    Icon(
                        Icons.Rounded.Edit,
                        null,
                        modifier = Modifier.size(AppTheme.indents.x3),
                        tint = AppTheme.colors.primaryText()
                    )
                }
            }
        }

        SpacerComponent { x6 }
        Text(
            text = sum,
            color = AppTheme.colors.primaryText(),
            style = AppTheme.typography.headingMegaLarge
        )
        SpacerComponent { x0_5 }
        Text(
            text = name,
            color = AppTheme.colors.secondaryText(),
            style = AppTheme.typography.bodySmall
        )
    }
}

@Composable
private fun SourceDetailedPanel(
    source: SourceDomainModel,
    onSourceClick: (String) -> Unit,
    onDeleteSourceClick: (String) -> Unit,
) {
    SwipeDismissComponent(
        modifier = Modifier.fillMaxWidth(),
        onDeleteClick = {
            onDeleteSourceClick(source.id)
        }
    ) {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x3)
                .clickable { onSourceClick(source.id) }) {
            Box(
                Modifier.size(AppTheme.indents.x6)
                    .background(AppTheme.colors.backgroundSecondary(), AppTheme.shapes.x2)
            ) {
                Icon(
                    if (source.originalCurrency.crypto) {
                        Icons.Rounded.Savings
                    } else {
                        Icons.Rounded.AccountBalance
                    },
                    null,
                    modifier = Modifier.size(AppTheme.indents.x3).align(Alignment.Center),
                    tint = if (source.originalCurrency.crypto) {
                        AppTheme.colors.accent2Color()
                    } else {
                        AppTheme.colors.accentColor()
                    }
                )
            }
            SpacerComponent { x2 }
            Column(Modifier.weight(1f)) {
                Text(
                    text = source.name,
                    color = AppTheme.colors.primaryBackgroundText(),
                    style = AppTheme.typography.headingMedium
                )
                SpacerComponent { x0_5 }
                Text(
                    text = source.originalFormattedSum,
                    color = AppTheme.colors.secondaryBackgroundText(),
                    style = AppTheme.typography.bodySmall
                )
            }
        }
    }
}