package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.main.presentation.MainScreenState

@Composable
@Preview
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            MainScreenState.Default(
                getMockSourceListDomainModel(),
            )
        )
    }
}