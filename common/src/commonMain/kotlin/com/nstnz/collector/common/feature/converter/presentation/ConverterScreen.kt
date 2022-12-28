package com.nstnz.collector.common.feature.converter.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.CurrencyExchange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.design.input.SumTextInputComponent
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.feature.core.domain.model.CurrencySumDomainModel

@Composable
internal fun ConverterScreen(
    viewState: ConverterScreenState,
    onMainTabClick: () -> Unit = {},
    onSettingsTabClick: () -> Unit = {},
    onChangeCurrencyClick: () -> Unit = {},
    onChangeSum: (String) -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            DefaultNavComponent(
                title = "Обменник",
                showBackButton = false
            )
        },
        bottomBar = {
            NavigationBarComponent(
                converterTabSelected = true,
                mainTabClick = onMainTabClick,
                converterTabClick = {},
                settingsTabClick = onSettingsTabClick
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val textValue = remember { mutableStateOf(TextFieldValue(viewState.sum)) }

            SpacerComponent { x3 }
            TextSelectorComponent(
                modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
                label = "Валюта",
                text = viewState.currency?.code.orEmpty(),
                onClick = {
                    onChangeCurrencyClick()
                }
            )
            SpacerComponent { x2 }
            SumTextInputComponent(
                modifier = Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x3),
                value = textValue.value,
                onValueChange = {
                    textValue.value = it
                    onChangeSum(it.text)
                },
                currencyCode = viewState.currency?.codeToShow.orEmpty(),
                currencyStr = viewState.currency?.name.orEmpty()
            )
            SpacerComponent { x3 }
            Icon(
                Icons.Rounded.CurrencyExchange,
                null,
                modifier = Modifier.size(AppTheme.indents.x3).align(Alignment.CenterHorizontally),
                tint = AppTheme.colors.accentColor()
            )
            SpacerComponent { x3 }
            viewState.exchangeList.forEach {
                CurrencyCell(
                    currency = it
                )
                SpacerComponent { x2 }
            }
        }
    }
}


@Composable
internal fun CurrencyCell(
    currency: CurrencySumDomainModel,
) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x3)
    ) {
        Box(
            Modifier.size(AppTheme.indents.x6)
                .background(AppTheme.colors.backgroundSecondary(), AppTheme.shapes.x2)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = currency.currency.codeToShow,
                color = (if (currency.currency.crypto) {
                    AppTheme.colors.accent2Color()
                } else {
                    AppTheme.colors.accentColor()
                }),
                style = AppTheme.typography.headingMedium
            )
        }
        SpacerComponent { x2 }
        Column(Modifier.weight(1f).align(Alignment.CenterVertically)) {
            Text(
                text = currency.formattedSum,
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMedium
            )
        }
    }
}