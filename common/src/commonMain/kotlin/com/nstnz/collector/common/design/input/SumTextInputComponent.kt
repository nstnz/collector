package com.nstnz.collector.common.design.input

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.nstnz.collector.common.design.input.internal.OutlinedTextField
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*

@Composable
internal fun SumTextInputComponent(
    currencyCode: String,
    currencyStr: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    Column(
        modifier.background(AppTheme.colors.backgroundSecondary(), AppTheme.shapes.x2)
            .padding(bottom = AppTheme.indents.x0_5, end = AppTheme.indents.x2)
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                interactionSource = interactionSource,
                value = value,
                onValueChange = { currentText ->
                    if (currentText.text.length <= 13) {
                        onValueChange(currentText)
                    }
                },
                placeholder = {
                    Text(
                        text = "0",
                        style = AppTheme.typography.headingMegaLarge,
                    )
                },
                isError = false,
                modifier = Modifier.width(IntrinsicSize.Min),
                textStyle = AppTheme.typography.headingMegaLarge,
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                keyboardActions = KeyboardActions.Default,
                visualTransformation = VisualTransformation.None,
                colors = getTextColors(),
                shape = AppTheme.shapes.x2,
                trailingIcon = null,
                enabled = true
            )
            Text(
                text = currencyCode.uppercase(),
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMedium,
                modifier = Modifier.align(Alignment.Bottom).padding(bottom = AppTheme.indents.x2_5)
                    .offset(x = AppTheme.indents.x1_5 * -1)
            )
        }
        Text(
            text = currencyStr,
            color = AppTheme.colors.secondaryBackgroundText(),
            style = AppTheme.typography.bodySmall,
            modifier = Modifier.padding(start = AppTheme.indents.x2)
                .offset(y = AppTheme.indents.x1_5 * -1)
        )
    }
}

@Composable
private fun getTextColors() =
    TextFieldDefaults.outlinedTextFieldColors(
        textColor = AppTheme.colors.primaryBackgroundText(),
        focusedBorderColor = AppTheme.colors.transparent(),
        unfocusedBorderColor = AppTheme.colors.transparent(),
        focusedLabelColor = AppTheme.colors.transparent(),
        unfocusedLabelColor = AppTheme.colors.transparent(),
        placeholderColor = AppTheme.colors.secondaryBackgroundText(),
        cursorColor = AppTheme.colors.accentColor(),
        disabledTextColor = AppTheme.colors.transparent(),
        disabledBorderColor = AppTheme.colors.transparent(),
        disabledLabelColor = AppTheme.colors.transparent(),
    )