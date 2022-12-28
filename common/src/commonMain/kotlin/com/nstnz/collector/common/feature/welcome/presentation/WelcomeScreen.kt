package com.nstnz.collector.common.feature.welcome.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.presentation.CurrencyCell
import com.nstnz.collector.common.feature.currencies.presentation.SearchPanel
import kotlinx.coroutines.delay

@Composable
internal fun WelcomeScreen(
    viewState: WelcomeScreenState,
    onCurrencyClick: (CurrencyDomainModel) -> Unit = {},
    onOkClick: () -> Unit = {},
    onSearch: (String) -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            NavBarComponent(
                modifier = Modifier.background(AppTheme.colors.backgroundPrimary()),
                title = "",
                actions = {
                    IconButton(onClick = onOkClick) {
                        Icon(
                            Icons.Rounded.Done,
                            null,
                            modifier = Modifier.size(AppTheme.indents.x3_5),
                            tint = AppTheme.colors.accentColor()
                        )
                    }
                }
            )
        }
    ) {
        val textValue = remember { mutableStateOf(TextFieldValue(viewState.searchText.orEmpty())) }
        Column(Modifier.fillMaxSize()) {
            SpacerComponent { x2 }
            Text(
                text = "Добро пожаловать!",
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMegaLarge,
                modifier = Modifier.padding(horizontal = AppTheme.indents.x3)
            )
            SpacerComponent { x1 }
            Text(
                text = "Выберите дефолтную валюту",
                color = AppTheme.colors.secondaryBackgroundText(),
                style = AppTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = AppTheme.indents.x3)
            )
            SpacerComponent { x5 }
            SearchPanel(
                textValue, onSearch
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = AppTheme.indents.x3,
                )
            ) {
                items(count = viewState.filteredCurrencies.size) { index ->
                    val currency = viewState.filteredCurrencies[index]
                    SpacerComponent { x2 }
                    CurrencyCell(
                        currency,
                        currency.code == viewState.checkedCode,
                        false,
                        onCurrencyClick
                    )
                }
            }
            SpacerComponent { x2 }
        }
    }
}