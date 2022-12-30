package com.nstnz.collector.common.feature.converter.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.nstnz.collector.common.design.input.SumTextInputComponent
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.feature.core.domain.model.CurrencySumDomainModel
import com.nstnz.collector.common.format

@Composable
internal fun ConverterScreen(
    viewState: ConverterScreenState,
    onMainTabClick: () -> Unit = {},
    onSettingsTabClick: () -> Unit = {},
    onChangeCurrencyClick: () -> Unit = {},
    onChangeSecondCurrencyClick: () -> Unit = {},
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
            Row(Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth()) {
                SumTextInputComponent(
                    modifier = Modifier.weight(1f),
                    value = textValue.value,
                    onValueChange = {
                        textValue.value = it
                        onChangeSum(it.text)
                    },
                    currencyCode = "",
                    currencyStr = viewState.currency?.name.orEmpty()
                )
                SpacerComponent { x2 }
                Row(Modifier.height(AppTheme.indents.x11_5).noEffectsClickable {
                    onChangeCurrencyClick()
                }
                    .border(
                        BorderStroke(
                            width = AppTheme.indents.x0_25,
                            brush = SolidColor(AppTheme.colors.backgroundSecondary())
                        ),
                        AppTheme.shapes.x2
                    )
                    .padding(AppTheme.indents.x2)) {
                    Text(
                        text = viewState.currency?.code.orEmpty(),
                        style = AppTheme.typography.headingMedium,
                        color = AppTheme.colors.primaryBackgroundText(),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            SpacerComponent { x3 }
            Icon(
                Icons.Rounded.CurrencyExchange,
                null,
                modifier = Modifier.size(AppTheme.indents.x3).align(Alignment.CenterHorizontally),
                tint = AppTheme.colors.accentColor()
            )
            SpacerComponent { x3 }
            Row(Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth()) {
                SumTextInputComponent(
                    modifier = Modifier.weight(1f),
                    value = TextFieldValue(format(viewState.exchange?.sum ?: 0.0)),
                    onValueChange = {},
                    currencyCode = "",
                    currencyStr = viewState.exchange?.currency?.name.orEmpty(),
                    enabled = false
                )
                SpacerComponent { x2 }
                Row(Modifier.height(AppTheme.indents.x11_5).noEffectsClickable {
                    onChangeSecondCurrencyClick()
                }
                    .border(
                        BorderStroke(
                            width = AppTheme.indents.x0_25,
                            brush = SolidColor(AppTheme.colors.backgroundSecondary())
                        ),
                        AppTheme.shapes.x2
                    )
                    .padding(AppTheme.indents.x2)) {
                    Text(
                        text = viewState.exchange?.currency?.code.orEmpty(),
                        style = AppTheme.typography.headingMedium,
                        color = AppTheme.colors.primaryBackgroundText(),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
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