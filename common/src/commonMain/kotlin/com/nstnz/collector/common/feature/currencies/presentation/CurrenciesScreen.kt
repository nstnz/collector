package com.nstnz.collector.common.feature.currencies.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.input.TextInputComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel

@Composable
internal fun CurrenciesScreen(
    viewState: CurrenciesScreenState,
    onCurrencyClick: (CurrencyDomainModel) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onBackCLick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            DefaultNavComponent(
                title = "Список валют", onBackClick = onBackCLick
            )
        },
    ) {
        val textValue = remember { mutableStateOf(TextFieldValue(viewState.searchText)) }
        Column(
            Modifier
                .fillMaxSize()
        ) {
            SpacerComponent { x2 }
            SearchPanel(
                textValue,
                onSearch
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = AppTheme.indents.x3,
                    top = AppTheme.indents.x3,
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
private fun SearchPanel(
    textValue: MutableState<TextFieldValue>,
    onSearch: (String) -> Unit = {},
) {
    TextInputComponent(
        modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
        placeholder = "Поиск",
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onSearch(it.text)
        },
        trailingIcon = {
            if (textValue.value.text.isEmpty()) {
                IconButton(
                    modifier = Modifier.padding(end = AppTheme.indents.x0_5),
                    onClick = {}
                ) {
                    Icon(
                        Icons.Rounded.Search,
                        null,
                        tint = AppTheme.colors.secondaryBackgroundText()
                    )
                }
            } else {
                IconButton(
                    onClick = { onSearch("") },
                    modifier = Modifier.padding(end = AppTheme.indents.x0_5)
                ) {
                    Icon(
                        Icons.Rounded.Cancel,
                        null,
                        tint = AppTheme.colors.backgroundSecondary()
                    )
                }
            }
        }
    )
}

@Composable
private fun CurrencyCell(
    currency: CurrencyDomainModel,
    selected: Boolean,
    multiCheck: Boolean,
    onCurrencyClick: (CurrencyDomainModel) -> Unit
) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x3)
            .clickable { onCurrencyClick(currency) }) {
        Box(
            Modifier.size(AppTheme.indents.x6)
                .background(AppTheme.colors.backgroundSecondary(), AppTheme.shapes.x2)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = currency.codeToShow,
                color = if (currency.crypto) {
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
                text = currency.name,
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMedium
            )
        }
    }
}