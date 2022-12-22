package com.nstnz.collector.common.design.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.button.PrimaryButtonColors.backgroundColor
import com.nstnz.collector.common.design.theme.AppTheme

@Composable
internal fun BottomButtonComponent(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    icon: @Composable (() -> Unit)? = null,
    state: ButtonState = ButtonState.DEFAULT,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val buttonShape = AppTheme.shapes.x4_top

    Surface(
        shape = buttonShape,
        contentColor = PrimaryButtonColors.textColor,
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = state == ButtonState.DEFAULT,
            )
    ) {
        Box(
            Modifier
                .background(AppTheme.gradients.accentGradient())
                .height(AppTheme.indents.x7_5)
                .indication(interactionSource, rememberRipple())
        ) {
            val contentModifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = AppTheme.indents.x2)

            ButtonContent(
                modifier = contentModifier,
                icon = icon,
                text = text,
                textStyle = AppTheme.typography.labelMedium,
                colors = PrimaryButtonColors,
                state = state,
            )
        }
    }
}