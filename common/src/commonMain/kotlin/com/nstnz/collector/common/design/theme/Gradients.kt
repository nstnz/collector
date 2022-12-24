package com.nstnz.collector.common.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

object Gradients {

    @Composable
    fun backgroundScreen() = Brush.verticalGradient(
        0.0f to AppTheme.colors.gradientPartsTop(),
        1.0f to AppTheme.colors.gradientPartsBottom()
    )

    @Composable
    fun secondaryBackgroundScreen() = Brush.verticalGradient(
        0.0f to AppTheme.colors.backgroundPrimary(),
        1.0f to AppTheme.colors.backgroundPrimary()
    )

    @Composable
    fun accentGradient() = Brush.verticalGradient(
        0.0f to AppTheme.colors.accentColor(),
        1.0f to AppTheme.colors.accentPressedColor()
    )
}