package com.nstnz.collector.common.design.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.backgroundPrimary

@Composable
internal fun CardComponent(
    modifier: Modifier = Modifier,
    elevation: Dp = AppTheme.elevations.card,
    color: Color = AppTheme.colors.backgroundPrimary(),
    shape: Shape = AppTheme.shapes.x4_5_bottom,
    content: @Composable () -> Unit
) {
    Surface(
        color = color,
        shape = shape,
        elevation = elevation,
        modifier = modifier
    ) {
        content()
    }
}