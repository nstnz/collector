package com.nstnz.collector.common.feature.editcount.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.nstnz.collector.common.design.input.SumTextInputComponent
import com.nstnz.collector.common.design.scaffold.BottomSheetComponent
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.invokeOnCompletion

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun EditCountScreen(
    viewState: EditCountScreenState,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onChangeSum: (String) -> Unit = {},
    onAddSum: (Double) -> Unit = {},
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
            textValue.value = TextFieldValue(viewState.sum, selection = TextRange(viewState.sum.length))

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
            SpacerComponent { x3 }
            Row(modifier = Modifier.fillMaxWidth()) {
                AddSumButton("500", Modifier.weight(1f), onAddSum)
                SpacerComponent { x2 }
                AddSumButton("1000", Modifier.weight(1f), onAddSum)
            }
            SpacerComponent { x2 }
            Row(modifier = Modifier.fillMaxWidth()) {
                AddSumButton("5000", Modifier.weight(1f), onAddSum)
                SpacerComponent { x2 }
                AddSumButton("10000", Modifier.weight(1f), onAddSum)
            }
        }
    }
}

@Composable
private fun AddSumButton(
    sum: String,
    modifier: Modifier,
    onClick: (Double) -> Unit
) {
    Box(
        modifier.background(
            AppTheme.colors.backgroundSecondary(),
            AppTheme.shapes.x2
        ).height(AppTheme.indents.x8)
            .clickable {
                onClick(sum.toDoubleOrNull() ?: 0.0)
            }
    ) {
        Text(
            text = "+ $sum",
            color = AppTheme.colors.primaryBackgroundText(),
            style = AppTheme.typography.headingMedium,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}
