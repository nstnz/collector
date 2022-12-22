package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.test.Theme

@Composable
@Preview
private fun MainScreenPreview() {
    Theme {
        MainScreen()
    }
}