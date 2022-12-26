package com.nstnz.collector.common.feature.editsource.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.NavigateNext
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
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.source.domain.model.SourceFundModel
import com.nstnz.collector.common.feature.source.domain.model.SourceModel

@Composable
internal fun EditSourceScreen(
    viewState: EditSourceScreenState,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onChangeName: (String) -> Unit = {},
    onChangeDefaultCurrency: (CurrencyEntity) -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            NavBarComponent(
                modifier = Modifier.background(AppTheme.colors.backgroundPrimary()),
                title = "",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Rounded.ArrowBackIosNew,
                            null,
                            modifier = Modifier.size(AppTheme.indents.x3_5),
                            tint = AppTheme.colors.primaryBackgroundText()
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            Icons.Rounded.Delete,
                            null,
                            modifier = Modifier.size(AppTheme.indents.x4_5),
                            tint = AppTheme.colors.primaryBackgroundText()
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomButtonComponent(
                text = "Ololo",
                onClick = {
                    onSaveClick()
                }
            )
        }
    ) {
        if (viewState is EditSourceScreenState.Default) {
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
                        label = "Edit currency",
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
