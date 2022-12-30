package com.nstnz.collector.common.feature.addsource.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.basic.di.strings
import com.nstnz.collector.common.design.input.TextInputComponent
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.input.internal.TextInputState
import com.nstnz.collector.common.design.scaffold.BottomSheetComponent
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.primaryBackgroundText
import com.nstnz.collector.common.design.theme.secondaryBackgroundText
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenState
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel

@Composable
internal fun AddSourceScreen(
    viewState: AddSourceScreenState,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onChangeName: (String) -> Unit = {},
    onChangeDefaultCurrency: (CurrencyDomainModel) -> Unit = {},
) {
    BottomSheetComponent(
        title = strings.Source_AddSourceTitle,
        description = strings.Source_AddSourceDesc,
        onCloseClick = onBackClick,
        onOkClick = onSaveClick
    ) {
        if (viewState is AddSourceScreenState.Default) {
            val textValue = remember { mutableStateOf(TextFieldValue(viewState.name)) }
            TextInputComponent(
                modifier = Modifier.fillMaxWidth(),
                placeholder = strings.Core_Name,
                label = strings.Source_AddSourceNameHint,
                value = textValue.value,
                onValueChange = {
                    textValue.value = it
                    onChangeName(it.text)
                },
                textFieldState = if (viewState.showNameError) {
                    TextInputState.Error(strings.Source_ErrorEmptyName)
                } else {
                    TextInputState.Default
                }
            )
            SpacerComponent { x2 }
            TextSelectorComponent(
                modifier = Modifier.fillMaxWidth(),
                label = strings.Core_DefaultCurrency,
                text = viewState.currency.code,
                onClick = {
                    onChangeDefaultCurrency(viewState.currency)
                }
            )
        }
    }
}