package com.nstnz.collector.common.feature.editsource.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.basic.di.strings
import com.nstnz.collector.common.design.input.TextInputComponent
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.input.internal.TextInputState
import com.nstnz.collector.common.design.scaffold.BottomSheetComponent
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun EditSourceScreen(
    viewState: EditSourceScreenState,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onChangeName: (String) -> Unit = {},
    onChangeDefaultCurrency: (CurrencyDomainModel) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val onSaveFieldsClick = {
        keyboardController?.hide()
        onSaveClick()
    }
    val onBackActionClick = {
        keyboardController?.hide()
        onBackClick()
    }

    BottomSheetComponent(
        title = strings.Source_EditSourceTitle,
        description = strings.Source_EditSourceDesc,
        onCloseClick = onBackActionClick,
        onOkClick = onSaveFieldsClick
    ) {
        if (viewState is EditSourceScreenState.Default) {
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
                textFieldState = TextInputState.Default
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
