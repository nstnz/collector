package com.nstnz.collector.common.design.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.nstnz.collector.common.design.input.internal.OutlinedTextField
import com.nstnz.collector.common.design.input.internal.TextInputState
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*

@Suppress("LongMethod")
@Composable
fun TextInputComponent(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    hint: String = "",
    label: String = "",
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textFieldState: TextInputState = TextInputState.Default,
    hasClearButton: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    maxLength: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    minHeight: Dp = AppTheme.indents.x7_5,
    minWidth: Dp = TextFieldDefaults.MinWidth,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column {
        val error = when (textFieldState) {
            TextInputState.Default -> null
            is TextInputState.Error -> textFieldState.error
        }
        val hasError = error != null
        val background = AppTheme.colors.transparent()

        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = AppTheme.typography.bodySmall,
                color = getTextColors()
                    .labelColor(
                        enabled = true,
                        error = hasError,
                        interactionSource = interactionSource
                    ).value
            )
            SpacerComponent { x0_5 }
        }
        OutlinedTextField(
            interactionSource = interactionSource,
            value = value,
            onValueChange = { currentText ->
                if (currentText.text.length <= maxLength) {
                    onValueChange(currentText)
                }
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = AppTheme.typography.inputMedium,
                )
            },
            isError = textFieldState is TextInputState.Error,
            modifier = modifier.fillMaxWidth()
                .defaultMinSize(
                    minHeight = minHeight,
                    minWidth = minWidth,
                )
                .background(
                    shape = AppTheme.shapes.x2,
                    color = background,
                ),
            textStyle = AppTheme.typography.inputMedium,
            singleLine = singleLine,
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            colors = getTextColors(),
            shape = AppTheme.shapes.x2,
            trailingIcon = trailingIcon ?: getTrailingIcon(
                hasClearButton,
                value,
                onValueChange
            ),
            enabled = enabled
        )
        ErrorContent(
            hasError = hasError,
            error = error
        )
        HintContent(
            hasError = hasError,
            hint = hint,
        )
    }
}

@Composable
private fun getTrailingIcon(
    hasClearButton: Boolean,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
): (@Composable () -> Unit)? =
    if (hasClearButton) {
        {
            IconButton(
                onClick = { onValueChange(value.copy("")) },
                modifier = Modifier.padding(end = AppTheme.indents.x0_5)
            ) {
                Icon(
                    Icons.Rounded.Cancel,
                    null,
                    tint = AppTheme.colors.dividerBackgroundText()
                )
            }
        }
    } else {
        null
    }

@Composable
private fun getTextColors() =
    TextFieldDefaults.outlinedTextFieldColors(
        textColor = AppTheme.colors.primaryBackgroundText(),
        focusedBorderColor = AppTheme.colors.accentColor(),
        unfocusedBorderColor = AppTheme.colors.dividerBackgroundText(),
        focusedLabelColor = AppTheme.colors.accentColor(),
        unfocusedLabelColor = AppTheme.colors.secondaryBackgroundText(),
        placeholderColor = AppTheme.colors.hintBackgroundText(),
        cursorColor = AppTheme.colors.accentColor()
    )

@Composable
private fun HintContent(
    hasError: Boolean,
    hint: String,
) {
    if (hint.isNotEmpty()) {
        AnimatedVisibility(visible = !hasError) {
            Text(
                text = hint,
                style = AppTheme.typography.bodyXsmall,
                color = AppTheme.colors.secondaryBackgroundText(),
                modifier = Modifier.padding(top = AppTheme.indents.x0_5),
            )
        }
    }
}

@Composable
private fun ErrorContent(
    hasError: Boolean,
    error: String?,
) {
    AnimatedVisibility(visible = hasError) {
        Text(
            text = error.orEmpty(),
            style = AppTheme.typography.bodyXsmall.copy(
                color = AppTheme.colors.backgroundError(),
            ),
            modifier = Modifier.padding(top = AppTheme.indents.x0_5),
        )
    }
}