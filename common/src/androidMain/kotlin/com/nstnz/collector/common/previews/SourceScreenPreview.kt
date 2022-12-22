package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreen

@Composable
@Preview
private fun SourceScreenPreview() {
    AppTheme {
        SourceScreen(
            sourceName = "Tinkoff"
        )
    }
}