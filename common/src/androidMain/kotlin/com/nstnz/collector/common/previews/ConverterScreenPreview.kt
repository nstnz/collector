package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.converter.presentation.ConverterScreen
import com.nstnz.collector.common.feature.converter.presentation.ConverterScreenState

@Composable
@Preview
private fun ConverterScreenPreview() {
    AppTheme {
        ConverterScreen(
            viewState = ConverterScreenState
        )
    }
}