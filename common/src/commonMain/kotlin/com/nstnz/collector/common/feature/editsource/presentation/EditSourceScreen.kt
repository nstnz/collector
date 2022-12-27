package com.nstnz.collector.common.feature.editsource.presentation

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
import androidx.compose.material.icons.rounded.Delete
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
import com.nstnz.collector.common.design.button.BottomButtonComponent
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.input.SumTextInputComponent
import com.nstnz.collector.common.design.input.TextInputComponent
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.input.internal.TextInputState
import com.nstnz.collector.common.design.scaffold.BottomSheetComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.backgroundPrimary
import com.nstnz.collector.common.design.theme.primaryBackgroundText
import com.nstnz.collector.common.design.theme.secondaryBackgroundText
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.editcount.presentation.EditCountScreenState

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
        title = "Редактировать аккаунт",
        description = "JKHkjshfk jahfkjahf kjahfk jahfkajfhkajhfkajshf",
        onCloseClick = onBackActionClick,
        onOkClick = onSaveFieldsClick
    ) {
        if (viewState is EditSourceScreenState.Default) {
            val textValue = remember { mutableStateOf(TextFieldValue(viewState.name)) }

            TextInputComponent(
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Name",
                label = "Имя аккаунта",
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
                label = "Валюта по умолчанию",
                text = viewState.currency.code,
                onClick = {
                    onChangeDefaultCurrency(viewState.currency)
                }
            )
        }
    }
}
