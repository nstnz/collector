package com.nstnz.collector.common.feature.source.presentation

import androidx.compose.runtime.Composable
import com.nstnz.collector.common.design.dialog.DialogComponent

@Composable
internal fun SelectCountEditModeDialog(
    onPlusMode: () -> Unit = {},
    onMinusMode: () -> Unit = {},
    onClose: () -> Unit = {},
) {
    DialogComponent(
        "Изменение баланса счета",
        "Выберите операцию, которую вы хотите совершить со счетом",
        "Пополнение",
        "Списание",
        "Отмена",
        onPlusMode,
        onMinusMode,
        onClose
    )
}