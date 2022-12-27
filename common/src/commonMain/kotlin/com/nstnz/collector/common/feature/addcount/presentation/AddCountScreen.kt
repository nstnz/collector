package com.nstnz.collector.common.feature.addcount.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.design.button.BottomButtonComponent
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.dialog.DialogComponent
import com.nstnz.collector.common.design.input.SumTextInputComponent
import com.nstnz.collector.common.design.input.TextInputComponent
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.scaffold.BottomSheetComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenState
import kotlinx.coroutines.NonCancellable.invokeOnCompletion

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun AddCountScreen(
    viewState: AddCountScreenState,
    onChangeSum: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onSelectCurrencyClick: () -> Unit = {},
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

    LaunchedEffect(viewState::class) {
        invokeOnCompletion { focusRequester.requestFocus() }
    }
    BottomSheetComponent(
        title = "Добавить счет",
        description = "JKHkjshfk jahfkjahf kjahfk jahfkajfhkajhfkajshf",
        onCloseClick = onBackActionClick,
        onOkClick = onSaveFieldsClick
    ) {
        if (viewState is AddCountScreenState.Default) {
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
            SpacerComponent { x2 }
            TextSelectorComponent(
                modifier = Modifier.fillMaxWidth(),
                label = "Валюта",
                text = viewState.currency.code,
                onClick = {
                    onSelectCurrencyClick()
                }
            )
        }
    }
}