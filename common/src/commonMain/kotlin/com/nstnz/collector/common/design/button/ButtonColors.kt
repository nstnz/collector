package com.nstnz.collector.common.design.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.nstnz.collector.common.design.theme.*

internal sealed interface ButtonColors {

    val backgroundColor: Color @Composable get
    val backgroundColorOnTap: Color @Composable get() = backgroundColor.copy(alpha = 0.7f)
    val backgroundColorDisabled: Color @Composable get
    val textColor: Color @Composable get
    val textColorDisabled: Color @Composable get
    val progressColor: Color @Composable get() = textColor
}

internal object PrimaryButtonColors : ButtonColors {

    override val backgroundColor: Color
        @Composable get() = AppTheme.colors.accentColor()
    override val backgroundColorDisabled: Color
        @Composable get() = AppTheme.colors.accentColor()
    override val textColor: Color
        @Composable get() = AppTheme.colors.primaryText()
    override val textColorDisabled: Color
        @Composable get() = AppTheme.colors.secondaryText()
}

internal object SecondaryButtonColors : ButtonColors {

    override val backgroundColor: Color
        @Composable get() = AppTheme.colors.backgroundSecondary()
    override val backgroundColorDisabled: Color
        @Composable get() = AppTheme.colors.backgroundSecondary()
    override val textColor: Color
        @Composable get() = AppTheme.colors.primaryBackgroundText()
    override val textColorDisabled: Color
        @Composable get() = AppTheme.colors.hintText()
}