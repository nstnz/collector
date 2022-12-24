package com.nstnz.collector.common.design.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipRect
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.backgroundPrimary

@Composable
internal fun CardComponent(
    modifier: Modifier = Modifier,
    shape: Shape = AppTheme.shapes.x4_5_bottom,
    content: @Composable () -> Unit
) {
    Surface(
        color = AppTheme.colors.backgroundPrimary(),
        shape = shape,
        elevation = AppTheme.elevations.card
    ) {
        content()
    }
}