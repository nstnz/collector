package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreen
import com.nstnz.collector.common.feature.addcount.presentation.AddCountScreenState
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreenState

@Composable
@Preview
private fun AddCountScreenPreview() {
    AppTheme {
        AddCountScreen(
            viewState = AddCountScreenState.Loading
        )
    }
}