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
import com.nstnz.collector.common.format

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
        title = if (viewState.isAdding) "Пополнить счет" else "Списать со счета",
        description = if (viewState.isAdding) "Введите сумму пополнения:" else "Введите сумму списания:",
        onCloseClick = onBackActionClick,
        onOkClick = onSaveFieldsClick
    ) {
        if (viewState is EditCountScreenState.Default) {
            val textValue = remember { mutableStateOf(TextFieldValue(viewState.sum)) }
            textValue.value =
                TextFieldValue(viewState.sum, selection = TextRange(viewState.sum.length))

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

            Text(
                text = "Текущая сумма на счету:",
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = viewState.sourceModel?.originalFormattedSum.orEmpty(),
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMedium,
                textAlign = TextAlign.Center
            )
            SpacerComponent { x2 }

            Text(
                text = "Сумма после изменения:",
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = format(viewState.futureTotal) + " ${viewState.sourceModel?.originalSum?.currency?.codeToShow}",
                color = if (viewState.futureTotal == (viewState.sourceModel?.originalSum?.sum
                        ?: 0.0)
                ) AppTheme.colors.primaryBackgroundText()
                else AppTheme.colors.accentColor(),
                style = AppTheme.typography.headingMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}
