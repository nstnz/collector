package com.nstnz.collector.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
internal expect fun imageResource(id: String): ImageBitmap
@Composable
internal expect fun imageVector(id: String): ImageVector
