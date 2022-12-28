package com.nstnz.collector.common.design.input

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessibleForward
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.noEffectsClickable

@Composable
internal fun TextSelectorComponent(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    hint: String = "",
    onClick: (String) -> Unit = {}
) {
    Box(modifier.fillMaxWidth().noEffectsClickable {
        onClick(text)
    }) {
        TextInputComponent(
            Modifier.fillMaxWidth(),
            enabled = false,
            value = TextFieldValue(text),
            placeholder = "",
            hint = hint,
            label = label,
            onValueChange = {},
            trailingIcon = {
                IconButton(
                    modifier = Modifier.padding(end = AppTheme.indents.x0_5),
                    onClick = {}
                ) {
                    Icon(
                        Icons.Rounded.NavigateNext,
                        null,
                        tint = AppTheme.colors.secondaryBackgroundText()
                    )
                }
            }
        )
    }
}