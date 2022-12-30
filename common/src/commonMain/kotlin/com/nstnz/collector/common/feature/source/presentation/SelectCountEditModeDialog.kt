package com.nstnz.collector.common.feature.source.presentation

import androidx.compose.runtime.Composable
import com.nstnz.collector.common.basic.di.strings
import com.nstnz.collector.common.design.dialog.DialogComponent

@Composable
internal fun SelectCountEditModeDialog(
    onPlusMode: () -> Unit = {},
    onMinusMode: () -> Unit = {},
    onClose: () -> Unit = {},
) {
    DialogComponent(
        strings.Count_ChangeBalanceTitle,
        strings.Count_ChangeBalanceDesc,
        strings.Count_AddButtonTitle,
        strings.Count_RemoveButtonTitle,
        strings.Core_Cancel,
        onPlusMode,
        onMinusMode,
        onClose
    )
}