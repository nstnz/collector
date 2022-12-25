package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreen
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenState
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreenState

@Composable
@Preview
private fun AddSourceScreenPreview() {
    AppTheme {
        AddSourceScreen(
            viewState = AddSourceScreenState.Default(
                "", CurrencyEntity(
                    "USD", "", false, false
                )
            )
        )
    }
}