package com.nstnz.collector.common.feature.editcount.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.design.input.SumTextInputComponent
import com.nstnz.collector.common.design.scaffold.BottomSheetComponent
import com.nstnz.collector.common.design.theme.invokeOnCompletion

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun EditCountScreen(
    viewState: EditCountScreenState,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onChangeSum: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val onSaveFieldsClick = {
        keyboardController?.hide()
        onSaveClick()
    }
    val onBackActionClick = {
        keyboardController?.hide()
        onBackClick()
    }

    LaunchedEffect(viewState::class) { invokeOnCompletion { focusRequester.requestFocus() } }
    BottomSheetComponent(
        title = "Редактировать счет",
        description = "JKHkjshfk jahfkjahf kjahfk jahfkajfhkajhfkajshf",
        onCloseClick = onBackActionClick,
        onOkClick = onSaveFieldsClick
    ) {
        if (viewState is EditCountScreenState.Default) {
            val textValue = remember { mutableStateOf(TextFieldValue(viewState.sum)) }

            SumTextInputComponent(
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                value = textValue.value,
                onValueChange = {
                    textValue.value = it
                    onChangeSum(it.text)
                },
                currencyCode = viewState.currency.codeToShow,
                currencyStr = viewState.currency.name
            )
        }
    }
}
