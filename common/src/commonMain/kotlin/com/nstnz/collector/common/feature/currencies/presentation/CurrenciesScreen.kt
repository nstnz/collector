package com.nstnz.collector.common.feature.currencies.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.button.BottomButtonComponent
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity

@Composable
internal fun CurrenciesScreen(
    viewState: CurrenciesScreenState,
    onCurrencyClick: (CurrencyDomainModel) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onBackCLick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = { DefaultNavComponent(onBackCLick) },
        bottomBar = {
            if (viewState.multiCheck) {
                BottomButtonComponent(
                    text = "Ololo",
                    onClick = {
                        onSaveClick()
                    }
                )
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            HintPanel(multiCheck = viewState.multiCheck)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = AppTheme.indents.x3,
                    start = AppTheme.indents.x3,
                    top = AppTheme.indents.x3,
                    end = AppTheme.indents.x3,
                )
            ) {
                items(count = viewState.filteredList.size) { index ->
                    val currency = viewState.filteredList[index]
                    CurrencyCell(
                        currency,
                        viewState.checkedCurrencies.any { it.code == currency.code },
                        viewState.multiCheck,
                        onCurrencyClick
                    )
                    SpacerComponent { x2 }
                }
            }
        }
    }
}

@Composable
private fun HintPanel(multiCheck: Boolean) {
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
private fun CurrencyCell(
    currency: CurrencyDomainModel,
    selected: Boolean,
    multiCheck: Boolean,
    onCurrencyClick: (CurrencyDomainModel) -> Unit
) {
    CardComponent(
        Modifier
            .fillMaxWidth(),
        shape = AppTheme.shapes.x2,
        elevation = AppTheme.elevations.secondaryCard,
    ) {
        Column(
            Modifier.fillMaxWidth()
                .clickable { onCurrencyClick(currency) }
                .padding(horizontal = AppTheme.indents.x3, vertical = AppTheme.indents.x2)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = currency.code,
                        color = if (!multiCheck && selected)
                            AppTheme.colors.accentColor() else AppTheme.colors.secondaryBackgroundText(),
                        style = AppTheme.typography.headingLarge
                    )
                    SpacerComponent { x0_5 }
                    Text(
                        text = currency.name,
                        color = if (!multiCheck && selected)
                            AppTheme.colors.accentColor() else AppTheme.colors.hintBackgroundText(),
                        style = AppTheme.typography.bodyMedium
                    )
                }
                if (multiCheck) {
                    SpacerComponent { x1 }
                    Icon(
                        if (selected) Icons.Rounded.CheckCircle else Icons.Rounded.RadioButtonUnchecked,
                        null,
                        modifier = Modifier.padding(top = AppTheme.indents.x0_5).size(AppTheme.indents.x3),
                        tint = AppTheme.colors.accentColor()
                    )
                }
            }
        }
    }
}