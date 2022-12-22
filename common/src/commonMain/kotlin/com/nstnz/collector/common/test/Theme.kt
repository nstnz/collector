package com.nstnz.collector.common.test

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun Theme(content: @Composable () -> Unit) {
    val orange = Color(0xFFFF8C00)
    val colors = lightColors(primary = orange)

    MaterialTheme(
        colors = colors
    ) {
        content()
    }
}
