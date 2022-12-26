package com.nstnz.collector.common.feature.addsource.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.design.button.BottomButtonComponent
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.input.TextInputComponent
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity

@Composable
internal fun AddSourceScreen(
    viewState: AddSourceScreenState,
    onBackClick: () -> Unit = {},
    onSaveClick: (String) -> Unit = {},
    onChangeName: (String) -> Unit = {},
    onChangeDefaultCurrency: (CurrencyDomainModel) -> Unit = {},
) {
    GradientScaffold(
        topBar = { DefaultNavComponent(onBackClick) },
        bottomBar = {
            BottomButtonComponent(
                text = "Ololo",
                onClick = {
                    onSaveClick("TODODOOD")
                }
            )
        }
    ) {
        if (viewState is AddSourceScreenState.Default) {
            val textValue = remember { mutableStateOf(TextFieldValue(viewState.name)) }
            CardComponent {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = AppTheme.indents.x3)
                ) {
                    SpacerComponent { x3 }
                    Text(
                        text = "JHKdhkjahdkjshd aasd",
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
                    TextInputComponent(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "Name",
                        label = "Enter name",
                        value = textValue.value,
                        onValueChange = {
                            textValue.value = it
                            onChangeName(it.text)
                        },
                    )
                    SpacerComponent { x2 }
                    TextSelectorComponent(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Default currency",
                        text = viewState.currency.code,
                        onClick = {
                            onChangeDefaultCurrency(viewState.currency)
                        }
                    )
                    SpacerComponent { x4 }
                }
            }
        }
    }
}