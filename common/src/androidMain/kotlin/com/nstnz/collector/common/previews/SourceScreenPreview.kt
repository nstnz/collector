package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.source.presentation.SourceScreen
import com.nstnz.collector.common.feature.source.presentation.SourceScreenState

@Composable
@Preview
private fun SourceScreenPreview() {
    AppTheme {
        SourceScreen(
            viewState = SourceScreenState.Default(
                getMockSourceListDomainModel().sources.first()
            )
        )
    }
}