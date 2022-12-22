package com.nstnz.collector.common.test

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import com.nstnz.collector.common.design.theme.AppTheme

internal val safeAreaState = mutableStateOf(PaddingValues())
internal val SafeArea = compositionLocalOf { safeAreaState }

@Composable
internal fun Content() {
    AppTheme {
        App()
    }
}
