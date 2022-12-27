package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreen
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenState

@Composable
@Preview
private fun AddSourceScreenPreview() {
    AppTheme {
        AddSourceScreen(
            viewState = AddSourceScreenState.Default(
                "", getMockCurrencyDomainModel()
            )
        )
    }
}