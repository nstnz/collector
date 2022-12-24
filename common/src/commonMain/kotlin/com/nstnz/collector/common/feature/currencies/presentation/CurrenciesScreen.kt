package com.nstnz.collector.common.feature.currencies.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.basic.texts.CurrenciesScreen_Title
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.basic.texts.MainScreen_Title
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity

@Composable
internal fun CurrenciesScreen(
    viewState: CurrenciesScreenState,
    onCurrencyClick: (CurrencyEntity) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onBackCLick: () -> Unit = {},
) {
    GradientScaffold(
        gradient = AppTheme.gradients.secondaryBackgroundScreen(),
        topBar = {
            NavBarComponent(
                titleColor = AppTheme.colors.primaryText(),
                title = CurrenciesScreen_Title,
                navigationIcon = {
                    IconButton(onClick = onBackCLick) {

                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
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
                    CurrencyCell(currency, viewState.multiCheck, onCurrencyClick)
                }
            }
        }
    }
}

@Composable
private fun CurrencyCell(
    currency: CurrencyEntity,
    multiCheck: Boolean,
    onCurrencyClick: (CurrencyEntity) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.indents.x1)
            .noEffectsClickable { onCurrencyClick(currency) }
    ) {
        Column {
            Text(
                text = currency.code,
                color = AppTheme.colors.primaryText(),
                style = AppTheme.typography.headingLarge
            )
            SpacerComponent { x0_5 }
            Text(
                text = currency.name,
                color = AppTheme.colors.primaryText(),
                style = AppTheme.typography.bodyMedium
            )
        }
        Spacer(Modifier.weight(1f))
        if (multiCheck) {
            Text(
                text = if (currency.isFavourite) "+" else "-",
                color = AppTheme.colors.primaryText(),
                style = AppTheme.typography.headingLarge
            )
        }
    }
}